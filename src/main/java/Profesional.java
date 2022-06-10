import java.time.LocalDate;
import java.util.*;

public class Profesional extends Usuario implements CrearTratamiento {
    /* *fechas
  -VerNuevosPacientes
  -CrearPlanDeControl
  -Asignar plan
  -ModificarPlanExistente
  -ControlPacientes
  -FinalizaroExtender
  -VerDatosPaciente(atributos, historial, etc)*/
    private UUID matricula;
    Scanner scan = new Scanner(System.in);
    HashSet<Paciente> pacientesAAtender = new HashSet<>();
    ArrayList<Paciente> pacientesACargo = new ArrayList<>(); ///es buena para persistencia? o mejor hashmap de todos?

    public Profesional(String nombreCompleto, TipoUsuario tipoUsuario, String DNI,
                       String contrasena, String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        matricula = UUID.randomUUID();
    }

    public void AlertaNoRealizacionAyerPacientes() {
        for (Paciente x : pacientesACargo) {
            if (x.isAlertaDeNoRealizacion())
                System.out.println(x.getNombre() + ", (dni:" + x.getDNI() + ") no cumplio con todas las tareas de ayer");
        }
    }

    public void infoPacientesAyer() {
        char seguir = 's';

        if (pacientesACargo.size() > 0) {
            for (Paciente x : pacientesACargo) {
                System.out.println(x.getNombre() + ", (dni:" + x.getDNI() + "), (enfermedad:" + x.getEnfermedad() + ")");
            }

            while (seguir == 's' || seguir == 'S') {
                System.out.println("Ingrese el dni del paciente para ver informacion de sus actividades de ayer:");
                String dni = scan.nextLine();

                for (Paciente pacientex : pacientesACargo) {
                    if (pacientex.getDNI().equals(dni)) {
                        pacientex.getHistorialMedico().get(pacientex.getHistorialMedico().size() - 1).infoTareasDiaX();
                        break;
                    }
                }
                System.out.println("Desea seguir viendo actividad de pacientes? s/n");
                seguir = scan.next().charAt(0);
                scan.nextLine();
            }
        } else
            System.out.println("No tiene ningun paciente a cargo por el momento.");
    }

    public void verNuevosPacientes(HashMap<String, Paciente> pacientes, ArrayList<PlanDeControl> planes) {
        int nuevos = 0;
        for (Paciente pacientex : pacientes.values()) {
            if (!pacientex.isAtendido() && matricula.equals(pacientex.getMatriculaMedico())) {
                System.out.println("Nombre:" + pacientex.getNombre() + "Enfermedad:" + pacientex.getEnfermedad()
                        + " DNI:" + pacientex.getDNI());
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
                    asignarPlan(pacientes, planes);
            } catch (InputMismatchException e) {
                System.out.println("Debiste ingresar un caracter");
            }
        }
    }

    public void asignarPlan(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes) {
        int opcion, i;
        boolean satisfactorio;
        char s_n = 's';
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);

        while (!pacientesAAtender.isEmpty() && (s_n == 's' || s_n == 'S')) {
            System.out.println(listaConver.get(0).nombreCompleto + "  enferemdad:" + listaConver.get(0).getEnfermedad());

            System.out.println("Que desea?\n 1:Asignar (o ver) plan predeterminado    2:Modificar (o ver) plan predeterminado" +
                    "    3: Crear plan nuevo     0:Salir");
            opcion = scan.nextInt();
            scan.nextLine();
            i = 0;

            switch (opcion) {
                case 1:
                    satisfactorio = asignarPredet(pacs, planes, i);
                    if (satisfactorio) {
                        pacientesACargo.add(listaConver.get(0));
                        listaConver.remove(0);
                    }
                    break;
                case 2:
                    satisfactorio = modificarPredetYAsignar(pacs, planes, i);
                    if (satisfactorio) {
                        pacientesACargo.add(listaConver.get(0));
                        listaConver.remove(0);
                    }
                    break;
                case 3:
                    crearTratamiento(pacs, planes);
                    pacientesACargo.add(listaConver.get(0));
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
                ///hacer deep copy
                pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(planes.get(i));
                pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(planes.get(i).getDias()));
                pacs.get(listaConver.get(0).getDNI()).setComparadorFecha(1);
                pacientesAAtender.remove(listaConver.get(0));
                satisfactorio = true;
            }
        } else {
            System.out.println("No existe plan predeterminado para esta enfermedad.");
            satisfactorio = false;
        }
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
                pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(dias));
                pacs.get(listaConver.get(0).getDNI()).setComparadorFecha(1);
                pacientesAAtender.remove(listaConver.get(0));
                System.out.println("Plan satisfactoriamente asignado.");
                satisfactorio = true;
            }
        } else {
            System.out.println("No existe plan predeterminado para esta enfermedad.");
            satisfactorio = false;
        }
        return satisfactorio;
    }

    public void extenderPlan() {
        int masDias;
        List<Paciente> listaConver = new ArrayList<>(pacientesACargo);
        System.out.println("Ingrese dni del paciente:");
        String dni = scan.nextLine();

        for (int i = 0; i < listaConver.size(); i++) {
            if (listaConver.get(i).getDNI().equals(dni)) {
                System.out.println("Cuantos dias desea extender el plan?");
                masDias = scan.nextInt();
                scan.nextLine();

                listaConver.get(i).setfFin(listaConver.get(i).getfFin().plusDays(masDias));
                break;
            }
        }
        System.out.println("Plan extendido exitosamente.");
    }

    public void finalizarPlan() {
        List<Paciente> listaConver = new ArrayList<>(pacientesACargo);
        System.out.println("Ingrese dni del paciente:");
        String dni = scan.nextLine();

        for (Paciente paciente : listaConver) {
            if (paciente.getDNI().equals(dni)) {
                paciente.resetPaciente();

                for (int z = 0; z < pacientesACargo.size(); z++) {
                    if (pacientesACargo.get(z).getDNI().equals(dni)) {
                        pacientesACargo.remove(z);
                        break;
                    }
                }
                System.out.println("Plan finalizado con exito");
            }
        }
    }

    public void SeleccionDePacientePorAtender() {
        String dni;
        System.out.println("Digite el dni del paciente del cual desee ver informacion");
        for (Paciente pacientex : pacientesAAtender) {
            System.out.println(pacientex.getNombre() + "  dni:" + pacientex.getDNI());
        }
        dni = scan.nextLine();
        buscarPacientePorDni(dni);
    }

    public void buscarPacientePorDni(String dni) {
        for (Paciente pacientex : pacientesACargo) {
            if (pacientex.getDNI().equals(dni)) {
                System.out.println(pacientex);
                /////buscar cosas
                break;
            }
        }
    }

    public UUID getMatricula() {
        return matricula;
    }

    public String getEdad() {
        return edad;
    }


    @Override
    public void crearTratamiento(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> p) {
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);

        System.out.println("Cantidad de dias del plan:");
        int dias = scan.nextInt();
        scan.nextLine();

        PlanDeControl plan = new PlanDeControl(listaConver.get(0).getEnfermedad(), dias);
        plan.agregarTareasPROADM();

        pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(plan);
        pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
        pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(dias));
        pacs.get(listaConver.get(0).getDNI()).setComparadorFecha(1);
        pacientesAAtender.remove(listaConver.get(0));
        ///sugerir predet a admin

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
}