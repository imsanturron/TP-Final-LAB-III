import java.util.HashMap;
import java.util.Scanner;

public class Administrador extends Usuario {
    Scanner scan = new Scanner(System.in);

    public Administrador(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena
            , String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);

    }

    public void ingresoPaciente(HashMap<String, Paciente> pacientes, HashMap<String, Profesional> profesionales) {///lista
        System.out.println("ingrese DNI del paciente:");
        String dni = scan.nextLine();
        System.out.println("ingrese el nombre del paciente:");
        String nombre = scan.nextLine();
        System.out.println("ingrese el telefono:");
        String tel = scan.nextLine();
        System.out.println("ingrese la edad:");
        String edadd = scan.nextLine();
        System.out.println("que enfermedad posee?:");
        String enfmd = scan.nextLine();
        Paciente pacientex = new Paciente(nombre, TipoUsuario.PACIENTE, dni, dni,
                tel, enfmd, asignarProfesional(profesionales), edadd);

        pacientes.put(dni, pacientex);
    }

    public void ingresoProfesional(HashMap profesionales) {///lista
        System.out.println("ingrese DNI del administrador:");
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
    }

    public void registroAdministrador(HashMap administradores) {///lista
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
    }

    public Profesional asignarProfesional(HashMap<String, Profesional> profesionales) {
        for (Profesional profesionalx : profesionales.values()) {
            return profesionalx;
        }
        return profesionales.get("ksamd");
    }

    public void darDeBaja() {

    }


/*    *fechas
-IngresoPaciente
-IngresoProfesional
-AdministracionEnfermedades
-AdministracionPlanesDeControl
-DarDeBaja
-CambiarEstadoPaciente
*/
}
