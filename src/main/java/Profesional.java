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

    public void infoAyerTareasPacientes() {
        for (Paciente x : pacientesACargo) {
            if (x.isAlertaDeNoRealizacion())
                System.out.println(x.getNombre() + " (dni:" + x.getDNI() + ") no cumplio con todas las tareas de ayer");
        }
    }

    public void verNuevosPacientes(HashMap<String, Paciente> pacientes, ArrayList<PlanDeControl> planes) {
        int nuevos = 0;
        for (Paciente pacientex : pacientes.values()) {
            if (!pacientex.isAtendido() && matricula.equals(pacientex.getMatriculaMedico())) {
                System.out.println("nombre:" + pacientex.getNombre() + "enfermedad:" + pacientex.getEnfermedad()
                        + " DNI:" + pacientex.getDNI());
                pacientesAAtender.add(pacientex);
                nuevos++;
            }
        }
        System.out.println("tienes " + nuevos + " nuevos pacientes.\ndeseas asignarles planes ahora? s/n");
        try {
            char opcion = scan.next().charAt(0);
            scan.nextLine();
            if (opcion == 'S' || opcion == 's')
                asignarPlan(pacientes, planes);
        } catch (InputMismatchException e) {
            System.out.println("debiste ingresar un caracter");
        }
    }

    public void asignarPlan(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes) {
        int opcion, i;
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
                    asignarPredet(pacs, planes, i);
                    listaConver.remove(0);
                    break;
                case 2:
                    modificarPredetYAsignar(pacs, planes, i);
                    listaConver.remove(0);
                    break;
                case 3:
                    crearTratamiento(pacs, planes);
                    listaConver.remove(0);
                    break;
            }
            System.out.println("desea seguir asignando planes? s/n");
            s_n = scan.next().charAt(0);
            scan.nextLine();
        }

        if (pacientesAAtender.isEmpty())
            System.out.println("No tienes pacientes nuevos!");
    }

    public void asignarPredet(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes, int i) {
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);
        char s_n;

        while (i < planes.size()) {
            if (planes.get(i).getEnfermedad().equalsIgnoreCase(listaConver.get(0).getEnfermedad())) {
                System.out.println("dias:" + planes.get(i).getDias());
                planes.get(i).verTareas();
                break;
            }
            i++;
        }
        if (i < planes.size()) {
            System.out.println("asignar? s/n");
            s_n = scan.next().charAt(0);
            scan.nextLine();
            if (s_n == 's' || s_n == 'S') {
                pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(planes.get(i));
                pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(planes.get(i).getDias()));
                pacientesAAtender.remove(listaConver.get(0));
            }
        } else
            System.out.println("No existe plan predeterminado para esta enfermedad.");
    }

    public void modificarPredetYAsignar(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes, int i) {
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);
        char s_n;
        int dias;

        while (i < planes.size()) {
            if (planes.get(i).getEnfermedad().equalsIgnoreCase(listaConver.get(0).getEnfermedad())) {
                System.out.println("dias:" + planes.get(i).getDias());
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
                System.out.println("de cuantos dias sera el plan?");
                dias = scan.nextInt();
                scan.nextLine();
                pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(planes.get(i).ModificarTareasYAsignarAuxPROADM(dias));
                pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
                pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(dias));
                pacientesAAtender.remove(listaConver.get(0));
                System.out.println("plan satisfactoriamente asignado.");
            }
        } else
            System.out.println("No existe plan predeterminado para esta enfermedad.");
    }

    public void SeleccionDePacientePorAtender() {
        String dni;
        System.out.println("digite el dni del paciente del cual desee ver informacion");
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

        System.out.println("cantidad de dias del plan:");
        int dias = scan.nextInt();
        scan.nextLine();

        PlanDeControl plan = new PlanDeControl(listaConver.get(0).getEnfermedad(), dias);
        plan.agregarTareasPROADM();

        pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(plan);
        pacs.get(listaConver.get(0).getDNI()).setfIni(LocalDate.now());
        pacs.get(listaConver.get(0).getDNI()).setfFin(LocalDate.now().plusDays(dias));
        pacientesAAtender.remove(listaConver.get(0));
        ///sugerir predet a admin

    }

    public HashSet<Paciente> getPacientesAAtender() {
        return pacientesAAtender;
    }

    public ArrayList<Paciente> getPacientesACargo() {
        return pacientesACargo;
    }
}