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
         */
    }
}