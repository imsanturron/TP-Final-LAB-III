import java.util.*;

public class Profesional extends Usuario {
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

    public Profesional(String nombreCompleto, TipoUsuario tipoUsuario, String DNI,
                       String contrasena, String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        matricula = UUID.randomUUID();
    }

    public void verNuevosPacientes(HashMap<String, Paciente> pacientes, ArrayList<PlanDeControl> planes) {
        int nuevos = 0;
        for (Paciente pacientex : pacientes.values()) {
            if (!pacientex.isAtendido() && matricula.equals(pacientex.getMatriculaMedico())) {
                pacientesAAtender.add(pacientex);
                nuevos++;
            }
        }
        System.out.println("tienes " + nuevos + " nuevos pacientes.\ndeseas asignarles planes ahora? s/n");
        try {
            char opcion = scan.next().charAt(0);
            if (opcion == 'S' || opcion == 's')
                asignarPlan(pacientes, planes);
        } catch (InputMismatchException e) {
            System.out.println("debiste ingresar un caracter");
        }
    }

    public void asignarPlan(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes) {
        int opcion, dias, i = 0;
        char s_n = 's';
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);
        System.out.println(listaConver);
        System.out.println(pacientesAAtender);///revisar!!!!!!

        while (!pacientesAAtender.isEmpty() && (s_n == 's' || s_n == 'S')) {
            System.out.println(listaConver.get(0).nombreCompleto + "  enferemdad:" + listaConver.get(0).getEnfermedad());

            System.out.println("Que desea?\n 1:Asignar (o ver) plan predeterminado    2:Modificar (o ver) plan predeterminado" +
                    "    3: Crear plan nuevo     0:Salir");
            opcion = scan.nextInt();
            scan.nextLine();
            i = 0;

            switch (opcion) {
                case 1:
                    while (i < planes.size()) {
                        if (planes.get(i).getEnfermedad().equalsIgnoreCase(listaConver.get(0).getEnfermedad())) {
                            planes.get(i).verTareas();
                            break;
                        }
                        i++;
                    }
                    if (i < planes.size()) {
                        System.out.println("asignar? s/n");
                        s_n = scan.next().charAt(0);
                        if (s_n == 's' || s_n == 'S') {
                            pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(planes.get(i));
                            pacientesAAtender.remove(listaConver.get(0));
                            listaConver.remove(0);
                        }
                    } else
                        System.out.println("No existe plan predeterminado para esta enfermedad.");
                    break;
                case 2:
                    while (i < planes.size()) {
                        if (planes.get(i).getEnfermedad().equalsIgnoreCase(listaConver.get(0).getEnfermedad())) {
                            planes.get(i).verTareas();
                            break;
                        }
                        i++;
                    }
                    if (i < planes.size()) {
                        System.out.println("Modificar y asignar? s/n");
                        s_n = scan.next().charAt(0);
                        if (s_n == 's' || s_n == 'S') {
                            System.out.println("de cuantos dias sera el plan?");
                            dias = scan.nextInt();
                            scan.nextLine();
                            pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(planes.get(i).ModificarTareasYAsignarAuxPROADM(dias));
                            pacientesAAtender.remove(listaConver.get(0));
                            listaConver.remove(0);
                            System.out.println("plan satisfactoriamente asignado.");
                        }
                    } else
                        System.out.println("No existe plan predeterminado para esta enfermedad.");
                    break;
                case 3:
                    System.out.println("cantidad de dias del plan:");
                    dias = scan.nextInt();
                    PlanDeControl plan = new PlanDeControl(listaConver.get(0).getEnfermedad(), dias);
                    plan.agregarTareasPROADM();
                    pacs.get(listaConver.get(0).getDNI()).setPlanDeControl(plan);
                    pacientesAAtender.remove(listaConver.get(0));
                    listaConver.remove(0);
                    ///sugerir predet a admin
                    break;
            }
            System.out.println("desea seguir asignando planes? s/n");
            s_n = scan.next().charAt(0);
        }

        if (pacientesAAtender.isEmpty())
            System.out.println("No tienes pacientes nuevos!");
    }

    public UUID getMatricula() {
        return matricula;
    }

    public String getEdad() {
        return edad;
    }


}
