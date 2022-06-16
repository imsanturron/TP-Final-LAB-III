import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Profesional extends Usuario implements CrearTratamiento {
    Scanner scan = new Scanner(System.in);

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate visto;

    HashSet<Paciente> pacientesAAtender = new HashSet<>();
    ArrayList<Paciente> pacientesACargo = new ArrayList<>();


    public Profesional() {
        super();
    }

    public Profesional(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena, String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        visto = LocalDate.now();
    }


    public void AlertaNoRealizacionAyerPacientes(HashMap<String, Paciente> pacientes) {
        if (pacientes != null && !visto.equals(LocalDate.now())) {
            visto = LocalDate.now();
            for (Paciente x : pacientes.values()) {
                if (x.getProfesionalPropio() != null && x.getProfesionalPropio().getDNI().equals(DNI)) {
                    if (x.isAlertaDeNoRealizacion()) {
                        System.out.println(x.getNombre() + ", (dni:" + x.getDNI() + ") no cumplio con todas las tareas de ayer");

                        if (x.getfCompare().equals(LocalDate.now()))
                            x.setAlertaDeNoRealizacion(false);

                        x.setTermina(true);
                    } else if (x.getfCompare() != null && !x.getfCompare().equals(LocalDate.now()) && !x.isTermina()) {
                        System.out.println(x.getNombre() + ", (dni:" + x.getDNI() + ") no cumplio con todas las tareas de ayer");
                        x.setTermina(true);
                    }
                    if (x.getfCompare() != null && (DAYS.between(x.getfCompare(), LocalDate.now())) > 1)
                        System.out.println("El paciente no abre la aplicacion hace mas de un dia.");
                }
            }
        }
        sacarPacientesFinalizados(pacientes);
    }

    public void sacarPacientesFinalizados(HashMap<String, Paciente> pacientes) {
        int i = 0;
        while (i < pacientesACargo.size()) {
            if (pacientes.containsKey(pacientesACargo.get(i).getDNI())) {
                if (pacientes.get(pacientesACargo.get(i).getDNI()).getPlanDeControl() == null) {
                    pacientesACargo.remove(i);
                }
            }
            i++;
        }
    }

    public void infoPacientesAyer(HashMap<String, Paciente> pacientes) {
        char seguir = 's', alerta;

        if (pacientesACargo.size() > 0) {
            for (Paciente x : pacientesACargo) {
                System.out.println(x.getNombre() + ", (dni:" + x.getDNI() + "), (enfermedad:" + x.getEnfermedad() + ")");
            }

            while (seguir == 's' || seguir == 'S') {
                alerta = 'x';
                System.out.println("Ingrese el dni del paciente para ver informacion de sus actividades de ayer:");
                String dni = scan.nextLine();

                for (Paciente pacientex : pacientesACargo) {
                    if (pacientex.getDNI().equals(dni)) {
                        alerta = 'n';
                        pacientex = pacientes.get(dni);
                        if (pacientex.getHistorialMedico().size() >= 1)
                            pacientex.getHistorialMedico().get(pacientex.getHistorialMedico().size() - 1).infoTareasDiaX();
                        else
                            System.out.println("Este paciente no posee historial medico actualmente.");

                        break;
                    }
                }
                if (alerta == 'x')
                    System.out.println("No se hallo un paciente con ese DNI");

                System.out.println("Desea seguir viendo actividad de pacientes? s/n");
                seguir = scan.next().charAt(0);
                scan.nextLine();
            }
        } else
            System.out.println("No tiene ningun paciente a cargo por el momento.");
    }

    public void verNuevosPacientes(HashMap<String, Paciente> pacientes, ArrayList<PlanDeControl> planes,
                                   ArrayList<String> enfermedades) {
        int nuevos = 0;
        for (Paciente pacientex : pacientes.values()) {
            if (!pacientex.isVisto() && DNI.equals(pacientex.getProfesionalPropio().getDNI())) {
                System.out.println("Nombre:" + pacientex.getNombre() + "  -  Enfermedad:" + pacientex.getEnfermedad()
                        + "  -  DNI:" + pacientex.getDNI());
                pacientex.setVisto(true);
                pacientesAAtender.add(pacientex);
                nuevos++;
            }
        }
        System.out.println("Tienes " + nuevos + " nuevos pacientes.");
        if (nuevos > 0) {
            System.out.println("Deseas asignarles planes ahora? s/n");
            try {
                char opcion = scan.next().charAt(0);
                scan.nextLine();
                if (opcion == 'S' || opcion == 's')
                    asignarPlan(pacientes, planes, enfermedades);
            } catch (InputMismatchException e) {
                System.out.println("Debiste ingresar un caracter");
            }
        }
    }

    public void pacientesVistosEnEspera() {
        char opcion;
        System.out.println("Tienes " + pacientesAAtender.size() + " pacientes en espera");
        if (pacientesAAtender.size() > 0) {
            System.out.println("Ver informacion de cada uno? s/n");
            opcion = scan.next().charAt(0);
            scan.nextLine();

            if (opcion == 's' || opcion == 'S') {
                System.out.println(pacientesAAtender);
            }
        }
    }

    public void asignarPlan(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes, ArrayList<String> enfermedades) {
        int opcion, i;
        boolean satisfactorio;
        char s_n = 's';
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);

        while (!pacientesAAtender.isEmpty() && (s_n == 's' || s_n == 'S')) {
            System.out.println("\nNombre:" + listaConver.get(0).nombreCompleto + "   Enferemdad:" + listaConver.get(0).getEnfermedad());

            System.out.println("Que desea?\n 1:Asignar (o ver) plan predeterminado    2:Modificar (o ver) plan predeterminado" +
                    "    3: Crear plan nuevo     0:Salir");
            opcion = scan.nextInt();
            scan.nextLine();
            i = 0;

            switch (opcion) {
                case 1:
                    satisfactorio = asignarPredet(pacs, planes, i);
                    if (satisfactorio) {
                        System.out.println("plan satisfactoriamente asignado.");
                        pacientesACargo.add(listaConver.get(0));
                        listaConver.get(0).setAtendido(true);
                        pacs.get(listaConver.get(0).getDNI()).setAtendido(true);
                        listaConver.remove(0);
                    }
                    break;
                case 2:
                    satisfactorio = modificarPredetYAsignar(pacs, planes, i);
                    if (satisfactorio) {
                        System.out.println("plan satisfactoriamente asignado.");
                        pacientesACargo.add(listaConver.get(0));
                        listaConver.get(0).setAtendido(true);
                        pacs.get(listaConver.get(0).getDNI()).setAtendido(true);
                        listaConver.remove(0);
                    }
                    break;
                case 3:
                    crearTratamiento(pacs, planes, enfermedades);
                    System.out.println("plan satisfactoriamente asignado.");
                    pacientesACargo.add(listaConver.get(0));
                    listaConver.get(0).setAtendido(true);
                    pacs.get(listaConver.get(0).getDNI()).setAtendido(true);
                    listaConver.remove(0);
                    break;
            }
            System.out.println("Desea seguir asignando planes? s/n");
            s_n = scan.next().charAt(0);
            scan.nextLine();
        }

        if (pacientesAAtender.isEmpty())
            System.out.println("No tienes pacientes nuevos!");
    }

    public boolean asignarPredet(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes, int i) {
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);
        char s_n;
        boolean satisfactorio = false;

        while (i < planes.size()) {
            if (planes.get(i).getEnfermedad().equalsIgnoreCase(listaConver.get(0).getEnfermedad())) {
                System.out.println("Dias:" + planes.get(i).getDias());
                planes.get(i).verTareas();
                break;
            }
            i++;
        }
        if (i < planes.size()) {
            System.out.println("Asignar? s/n");
            s_n = scan.next().charAt(0);
            scan.nextLine();
            if (s_n == 's' || s_n == 'S') {
                PlanDeControl cloned = (PlanDeControl) planes.get(i).clone();
                pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(cloned);
                pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfCompare(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(planes.get(i).getDias()));
                pacs.get(listaConver.get(0).getDNI()).setComparadorFecha(1);
                pacientesAAtender.remove(listaConver.get(0));
                satisfactorio = true;
            }
        } else
            System.out.println("No existe plan predeterminado para esta enfermedad.");

        return satisfactorio;
    }

    public boolean modificarPredetYAsignar(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes, int i) {
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);
        char s_n;
        int dias;
        boolean satisfactorio = false;

        while (i < planes.size()) {
            if (planes.get(i).getEnfermedad().equalsIgnoreCase(listaConver.get(0).getEnfermedad())) {
                System.out.println("Dias:" + planes.get(i).getDias());
                planes.get(i).verTareas();
                break;
            }
            i++;
        }
        if (i < planes.size()) {
            System.out.println("Modificar y asignar? s/n");
            s_n = scan.next().charAt(0);
            scan.nextLine();
            if (s_n == 's' || s_n == 'S') {
                System.out.println("De cuantos dias sera el plan?");
                dias = scan.nextInt();
                scan.nextLine();
                pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(planes.get(i).ModificarTareasYAsignarAuxPROADM(dias));
                pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfCompare(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(dias));
                pacs.get(listaConver.get(0).getDNI()).setComparadorFecha(1);
                pacientesAAtender.remove(listaConver.get(0));
                satisfactorio = true;
            }
        } else
            System.out.println("No existe plan predeterminado para esta enfermedad.");

        return satisfactorio;
    }

    public void extenderPlan(HashMap<String, Paciente> pacs) {
        int masDias = -99;
        System.out.println("Ingrese dni del paciente:");
        String dni = scan.nextLine();

        for (int i = 0; i < pacientesACargo.size(); i++) { ///no reemplazar for
            if (pacientesACargo.get(i).getDNI().equals(dni)) {
                System.out.println("Cuantos dias desea extender el plan?");
                masDias = scan.nextInt();
                scan.nextLine();
                pacs.get(dni).setfFin(pacs.get(dni).getfFin().plusDays(masDias));
                pacientesACargo.get(i).setfFin(pacientesACargo.get(i).getfFin().plusDays(masDias));
                System.out.println("Plan extendido exitosamente.");
                break;
            }
        }
        if (masDias == -99)
            System.out.println("No se encontro el paciente");
    }

    public void finalizarPlan(HashMap<String, Paciente> pacs) {
        System.out.println("Ingrese dni del paciente:");
        String dni = scan.nextLine();
        int elimina = -1;

        for (int i = 0; i < pacientesACargo.size(); i++) {
            if (pacientesACargo.get(i).getDNI().equalsIgnoreCase(dni)) {
                elimina = i;
                break;
            }
        }
        if (elimina != -1) {
            pacs.get(dni).resetPaciente();
            pacientesACargo.remove(elimina);
            System.out.println("Plan finalizado con exito");
        } else
            System.out.println("Paciente no encontrado");
    }


    public void verHistorialCompletoPaciente(HashMap<String, Paciente> pacs) {
        String dni;
        System.out.println("Ingrese dni del paciente para ver su historia medica:");
        dni = scan.nextLine();

        if (pacs.containsKey(dni)) {
            if (pacs.get(dni).getHistorialMedico().size() > 0) {
                System.out.println("Historia medica de " + pacs.get(dni).getNombre());
                for (int i = 0; i < pacs.get(dni).getHistorialMedico().size(); i++)
                    pacs.get(dni).getHistorialMedico().get(i).infoTareasDiaX();
            } else
                System.out.println("Este paciente no posee historial medico actualmente");
        } else
            System.out.println("No hay un paciente registrado con ese DNI.");
    }

    public void SeleccionDePacientePropioVerInfo() {
        String dni;
        boolean alerta = true;
        System.out.println("Digite el dni del paciente del cual desee ver informacion");
        for (Paciente pacientex : pacientesACargo) {
            System.out.println(pacientex.getNombre() + ",  dni:" + pacientex.getDNI());
        }
        dni = scan.nextLine();
        for (Paciente pacientex : pacientesACargo) {
            if (pacientex.getDNI().equalsIgnoreCase(dni)) {
                alerta = false;
                System.out.println("=====================================================================");
                System.out.println(pacientex.getNombre() + ":  DNI:" + pacientex.getDNI() + " / Enfermedad:" +
                        pacientex.getEnfermedad() + " / Edad:" + pacientex.getEdad());
                System.out.println("Plan asignado: fecha inicio:" + pacientex.getfIni() + " --- fecha finalizacion:" + pacientex.getfFin());
                System.out.println("Tareas:");
                pacientex.verTareasAHacer();
                System.out.println("=====================================================================");
            }
        }
        if (alerta)
            System.out.println("No se encontro el paciente.");
    }

    @Override
    public void crearTratamiento(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> p, ArrayList<String> enfermedades) {
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);

        System.out.println("Cantidad de dias del plan:");
        int dias = scan.nextInt();
        scan.nextLine();

        PlanDeControl plan = new PlanDeControl(listaConver.get(0).getEnfermedad(), dias);
        plan.agregarTareasPROADM();

        pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(plan);
        pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
        pacs.get(listaConver.get(0).getDNI()).setfCompare(LocalDate.now());
        pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(dias));
        pacs.get(listaConver.get(0).getDNI()).setComparadorFecha(1);
        pacientesAAtender.remove(listaConver.get(0));
    }

    public void sugerirAdminPlan(HashMap<String, Administrador> admins) { ///profesional NO puede predeterminar un plan
        String dni;
        char seguir = 's';
        while (seguir == 's' || seguir == 'S') {
            System.out.println("Ingrese el dni del paciente que posee el plan que le gustaria sugerir:");
            dni = scan.nextLine();
            for (Administrador x : admins.values()) {
                x.getSugerencias().add(dni);
            }
            System.out.println("Desea seguir sugiriendo planes al administrador? s/n");
            seguir = scan.next().charAt(0);
            scan.nextLine();
        }
    }

    public HashSet<Paciente> getPacientesAAtender() {
        return pacientesAAtender;
    }

    public ArrayList<Paciente> getPacientesACargo() {
        return pacientesACargo;
    }

    public void setPacientesAAtender(HashSet<Paciente> pacientesAAtender) {
        this.pacientesAAtender = pacientesAAtender;
    }

    public void setPacientesACargo(ArrayList<Paciente> pacientesACargo) {
        this.pacientesACargo = pacientesACargo;
    }

    public LocalDate getVisto() {
        return visto;
    }

    public void setVisto(LocalDate visto) {
        this.visto = visto;
    }
}