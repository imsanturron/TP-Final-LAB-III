import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrador extends Usuario implements CrearTratamiento {
    Scanner scan = new Scanner(System.in);
    private ArrayList<String> sugerencias = new ArrayList<>(); //no puede usarse static por serializacion, metodo cambiado.

    public Administrador() {
        super();
    }

    public Administrador(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                         String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
    }

    public void verSugerenciasPlanes(HashMap<String, Paciente> pacs, HashMap<String, Administrador> admins,
                                     ArrayList<PlanDeControl> planes) {
        char opcion, control = 'x';
        int i = 0;

        while (i < sugerencias.size()) {

            if (pacs.containsKey(sugerencias.get(i)) && pacs.get(sugerencias.get(i)).getPlanDeControl() != null) {
                System.out.println("\nUn profesional te sugirio que predetermines el siguiente plan:");
                System.out.println("----------------------------Plan n°" + (i + 1) + "-----------------------------");
                System.out.println("Enfermedad:" + pacs.get(sugerencias.get(i)).getEnfermedad() +
                        " // Dias del plan:" + pacs.get(sugerencias.get(i)).getPlanDeControl().getDias());
                pacs.get(sugerencias.get(i)).verTareasAHacer();
                System.out.println("-----------------------------------------------------------------");

                for (int j = 0; j < planes.size(); j++) {
                    if (planes.get(j).getEnfermedad().equalsIgnoreCase(pacs.get(sugerencias.get(i)).getEnfermedad())) {
                        control = 's';
                        System.out.println("Actualmente hay un plan para esta enfermedad, desea sobreescribirlo? s/n");
                        opcion = scan.next().charAt(0);
                        scan.nextLine();
                        if (opcion == 's' || opcion == 'S') {
                            planes.set(j, pacs.get(sugerencias.get(i)).getPlanDeControl());
                            System.out.println("Plan predeterminado exitosamente");
                        } else
                            System.out.println("Plan descartado");
                    }
                }
                if (control == 'x') {
                    System.out.println("Actualmente no existe plan por defecto para esta enfermedad. Asignar? s/n");
                    opcion = scan.next().charAt(0);
                    scan.nextLine();
                    if (opcion == 's' || opcion == 'S') {
                        planes.add(pacs.get(sugerencias.get(i)).getPlanDeControl());
                        System.out.println("Plan asignado con exito");
                    } else {
                        System.out.println("Plan descartado");
                    }
                }
            }
            for (Administrador x : admins.values()) {
                x.getSugerencias().remove(i);
            }
        }
    }

    public void ingresoPaciente(HashMap<String, Paciente> pacientes, HashMap<String, Profesional> profesionales,
                                HashMap<String, Usuario> usuarios, ArrayList<String> enfermedades) {///lista
        int i = 0;

        System.out.println("ingrese DNI del paciente:");
        String dni = scan.nextLine();
        System.out.println("ingrese el nombre del paciente:");
        String nombre = scan.nextLine();
        System.out.println("ingrese el telefono:");
        String tel = scan.nextLine();
        System.out.println("ingrese la edad:");
        String edadd = scan.nextLine();
        System.out.println("que enfermedad posee?");
        String enfmd = scan.nextLine();

        while (i < enfermedades.size()) {
            if (enfermedades.get(i).equalsIgnoreCase(enfmd))
                i = enfermedades.size() + 1;

            i++;
        }

        if (i == enfermedades.size() || enfermedades.size() == 0)
            enfermedades.add(enfmd);

        Profesional asignarPro = asignarProfesional(profesionales);

        Paciente pacientex = new Paciente(nombre, TipoUsuario.PACIENTE, dni, dni,
                tel, enfmd, asignarPro, edadd);

        pacientes.put(dni, pacientex);
        usuarios.put(dni, pacientex);
    }

    public void ingresoProfesional(HashMap<String, Profesional> profesionales, HashMap<String, Usuario> usuarios) {///lista
        System.out.println("ingrese DNI del profesional:");
        String dni = scan.nextLine();
        System.out.println("ingrese el nombre del profesional:");
        String nombre = scan.nextLine();
        System.out.println("ingrese el telefono:");
        String tel = scan.nextLine();
        System.out.println("ingrese la edad:");
        String edadd = scan.nextLine();
        Profesional profesionalx = new Profesional(nombre, TipoUsuario.PROFESIONAL, dni,
                dni, tel, edadd);

        profesionales.put(dni, profesionalx);
        usuarios.put(dni, profesionalx);
    }

    public void registroAdministrador(HashMap<String, Administrador> administradores, HashMap<String, Usuario> usuarios) {///lista
        System.out.println("ingrese DNI del administrador:");
        String dni = scan.nextLine();
        System.out.println("ingrese el nombre del administrador:");
        String nombre = scan.nextLine();
        System.out.println("ingrese el telefono:");
        String tel = scan.nextLine();
        System.out.println("ingrese la edad:");
        String edadd = scan.nextLine();

        Administrador administradorx = new Administrador(nombre, TipoUsuario.ADMINISTRADOR,
                dni, dni, tel, edadd);

        administradores.put(dni, administradorx);
        usuarios.put(dni, administradorx);
    }

    public void ingresoPacienteConocido(HashMap<String, Paciente> pacientes, HashMap<String, Profesional> profesionales,
                                        ArrayList<String> enfermedades) {
        int i = 0;
        System.out.println("Que enfermedad posee?");
        String enfmd = scan.nextLine();
        while (i < enfermedades.size()) {
            if (enfermedades.get(i).equalsIgnoreCase(enfmd))
                i = enfermedades.size() + 1;

            i++;
        }
        if (i == enfermedades.size())
            enfermedades.add(enfmd);

        System.out.println("Ingrese DNI del paciente");
        String dni = scan.nextLine();

        for (Paciente x : pacientes.values()) {
            if (x.getDNI().equalsIgnoreCase(dni)) {
                i = -9;
                x.setEnfermedad(enfmd);
                x.setProfesionalPropio(asignarProfesional(profesionales));
                System.out.println("Paciente asignado");
                break;
            }
        }
        if (i != -9)
            System.out.println("Paciente no hallado en el sistema");
    }

    public Profesional asignarProfesional(HashMap<String, Profesional> profesionales) {///asignar medico a un paciente
        String masterkey = "";
        int comparador = 99;

        for (String clave : profesionales.keySet()) {
            if (profesionales.get(clave).getPacientesACargo().size() < comparador) {
                comparador = profesionales.get(clave).getPacientesACargo().size();
                masterkey = clave;
            }
        }
        return profesionales.get(masterkey);
    }

    @Override
    public void crearTratamiento(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planesPredet,
                                 ArrayList<String> enfermedades) { ///ver si esta bien
        int i = 0;
        char opcion;
        System.out.println("Cantidad de dias del plan?");
        int dias = scan.nextInt();
        scan.nextLine();
        System.out.println("Enfermedad?");
        String enf = scan.nextLine();
        while (i < enfermedades.size()) {
            if (enfermedades.get(i).equalsIgnoreCase(enf))
                i = enfermedades.size() + 1;

            i++;
        }

        if (i == enfermedades.size() || enfermedades.size() == 0)
            enfermedades.add(enf);

        PlanDeControl plan = new PlanDeControl(enf, dias);
        plan.agregarTareasPROADM();

        planesPredet.add(plan);

        System.out.println("Asignar el plan a un paciente? s/n");
        opcion = scan.next().charAt(0);
        scan.nextLine();

        if (opcion == 's' || opcion == 'S') {
            System.out.println("Ingrese dni del paciente a asignar:");
            String dni = scan.nextLine();
            if (pacs.containsKey(dni))
                pacs.get(dni).setPlanDeControl(plan);

            System.out.println("Plan asignado satisfactoriamente");
        }
    }

    public void agregarEnfermedad(ArrayList<String> enfermedades) {
        System.out.println("ingrese la enfermedad a agregar:");
        String enfmd = scan.nextLine();
        if (!enfermedades.contains(enfmd))
            enfermedades.add(enfmd);
    }

    public ArrayList<String> getSugerencias() {
        return sugerencias;
    }

    public void setSugerencias(ArrayList<String> sugerencias) {
        this.sugerencias = sugerencias;
    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}
