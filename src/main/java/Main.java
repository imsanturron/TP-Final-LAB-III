import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
https://github.com/imsanturron/TP-Final-LAB-III.git
 */
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.menu();

        /*HashMap<String, Paciente> pacientes = new HashMap<>();  //hacerlas static?
        HashMap<String, Profesional> profesionales = new HashMap<>();
        HashMap<String, Administrador> administradores = new HashMap<>();
        ArrayList<String> enfermedades = new ArrayList<>();
        ArrayList<PlanDeControl> planesControl = new ArrayList<>();*/


        /*
        Profesional profesionalprueba = new Profesional("asd", TipoUsuario.PROFESIONAL, "DSD",
                "dsadff", "eafef3", "fefef");
        Paciente pacienteprueba = new Paciente("juan", TipoUsuario.PACIENTE, "1234", "DDDAS",
                "24124214", "gripe", profesionalprueba, "43");

        PlanDeControl planDeControl = new PlanDeControl("gripe", 6);
        planDeControl.agregarTareasPROADM();
        planDeControl.verTareas();


        profesionalprueba.asignarPlan(pacientes, planesControl);

                    Profesional x = new Profesional("sad", TipoUsuario.PROFESIONAL, "QE",
                    "ASDAF", "ASFAWF", "FAFAWF");
            Profesional profesionalprueba = new Profesional("DR carlos", TipoUsuario.PROFESIONAL, "D465",
                    "dsadff", "eafef3", "68");
            profesionalprueba.getPacientesACargo().add(new Paciente("paciente juan", TipoUsuario.PACIENTE, "999",
                    "DAFF", "DASD", "gripe", x, "44"));
            profesionales.put(profesionalprueba.getDNI(), profesionalprueba);
            Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());

         */
    }
}