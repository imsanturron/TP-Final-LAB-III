package facundo.gt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class Administrador extends Usuario {

    Scanner scan = new Scanner(System.in);


    public Administrador(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena
            , String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad,true);

    }

    public void ingresoPaciente(Profesional pr) {///lista
        String dni = scan.nextLine();
        Paciente pacientex = new Paciente(scan.nextLine(), TipoUsuario.PACIENTE, dni, dni.substring(4), ///?
                scan.nextLine(), scan.nextLine(),pr, scan.nextLine(),true);

        pr.setPaciente(pacientex);
    }

    public void ingresoProfesional(HashMap profesionales) {///lista
        String dni = scan.nextLine();
        Profesional profesionalx = new Profesional(scan.nextLine(), TipoUsuario.PROFESIONAL, dni,
                dni.substring(4), scan.nextLine(), scan.nextLine());

        profesionales.put(profesionalx.hashCode(), profesionalx);
    }

    public void registroAdministrador(HashMap administradores) {///lista
        String dni = scan.nextLine();
        Administrador administradorx = new Administrador(scan.nextLine(), TipoUsuario.ADMINISTRADOR,
                dni, dni.substring(4), scan.nextLine(), scan.nextLine());

        administradores.put(administradorx.hashCode(), administradorx);
    }

    public Profesional buscarProfesional(HashMap<Integer,Profesional> profesionales,String dni){
        Profesional p = null;
        for(Map.Entry<Integer,Profesional> entry : profesionales.entrySet()){
            if(entry.getValue().getDNI() == dni){
                p = entry.getValue();
                break;
            }
        }
        return p;
    }
    public String SeleccionDeProfesional(HashMap<Integer,Profesional> profesionales){
        System.out.println("digite el numero de dni del profesional a asignar");
        String dni;
        for(Map.Entry<Integer,Profesional> entry : profesionales.entrySet()){
            System.out.println("Matricula: "+ entry.getValue().getDNI()+ "Nombre del profesional: "+ entry.getValue().getNombreCompleto());
        }
        dni = scan.nextLine();

        return dni;

    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public void darDeBaja() {

    }

    @Override
    public String toString() {
        return "Administrador{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", DNI='" + DNI + '\'' +
                ", edad='" + edad + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
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
