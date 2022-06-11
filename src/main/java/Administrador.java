import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Administrador extends Usuario implements CrearTratamiento {
    Scanner scan = new Scanner(System.in);

    public Administrador() {
        super();
    }

    public Administrador(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena
            , String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
    }

    public void ingresoPaciente(HashMap<String, Paciente> pacientes, HashMap<String, Profesional> profesionales
            , HashMap<String, Usuario> usuarios) {///lista
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

    public Profesional asignarProfesional(HashMap<String, Profesional> profesionales) {
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

    public void darDeBaja() {
        int opcion = 99;
        System.out.println("dar de baja a:\n 1:Administrador  -  2:Paciente  -  3:Profesional   -   0:Salir");
        try {
            opcion = scan.nextInt();
            scan.nextLine();
            while (opcion != 0) {
                /////
            }
        } catch (IllegalArgumentException | SecurityException exc) {
            System.out.println("aaaaaaaaaaaa");
        }
    }

    @Override
    public void crearTratamiento(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planesPredet) { ///ver si esta bien
        System.out.println("cantidad de dias del plan?");
        int dias = scan.nextInt();
        scan.nextLine();
        System.out.println("enfermedad?");
        String enf = scan.nextLine();
        PlanDeControl plan = new PlanDeControl(enf, dias);
        plan.agregarTareasPROADM();

        planesPredet.add(plan);
    }

    public void agregarEnfermedad(ArrayList<String> enfermedades) {
        System.out.println("ingrese la enfermedad a agregar:");
        enfermedades.add(scan.nextLine());
    }

    public void borrarEnfermedad(ArrayList<String> enfermedades) {
        System.out.println("ingrese la enfermedad a borrar:");
        ///borrar
    }



/*    *fechas
-AdministracionEnfermedades
-AdministracionPlanesDeControl
-DarDeBaja
-CambiarEstadoPaciente
*/
}
