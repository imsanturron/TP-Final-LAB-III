import java.util.HashMap;
import java.util.Scanner;

public class Administrador extends Usuario {
    //Paciente pacient
    //Profesional profesional
    Scanner scan = new Scanner(System.in);
    public Administrador(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena
            , String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);

    }

    public void ingresoPaciente(HashMap<String, Paciente> pacientes, HashMap<String, Profesional> profesionales) {///lista
        String dni = scan.nextLine();
        Paciente pacientex = new Paciente(scan.nextLine(), TipoUsuario.PACIENTE, dni, dni, ///?
                scan.nextLine(), scan.nextLine(), asignarProfesional(profesionales), scan.nextLine());

        pacientes.put(dni, pacientex);
    }

    public void ingresoProfesional(HashMap profesionales) {///lista
        String dni;
        Profesional profesionalx = new Profesional(scan.nextLine(), TipoUsuario.PROFESIONAL, dni = scan.nextLine(),
                dni, scan.nextLine(), scan.nextLine());

        profesionales.put(dni, profesionalx);
    }

    public void registroAdministrador(HashMap administradores) {///lista
        String dni;
        Administrador administradorx = new Administrador(scan.nextLine(), TipoUsuario.ADMINISTRADOR,
                dni = scan.nextLine(), dni, scan.nextLine(), scan.nextLine());

        administradores.put(dni, administradorx);
    }

    public Profesional asignarProfesional(HashMap<String, Profesional> profesionales){
        for (Profesional profesionalx : profesionales.values()){
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
