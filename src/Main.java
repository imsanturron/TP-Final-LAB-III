import java.util.ArrayList;
import java.util.HashMap;

/*
https://github.com/imsanturron/TP-Final-LAB-III.git
 */
public class Main {
    public static void main(String[] args) {
        HashMap<String, Paciente> pacientes = new HashMap<>();
        HashMap<String, Profesional> profesionales = new HashMap<>();
        HashMap<String, Administrador> administradores = new HashMap<>();
        ArrayList<String> enfermedades = new ArrayList<>();
        ArrayList<PlanDeControl> planesControl = new ArrayList<>();
        System.out.println("asdasdsdas");

        Profesional profesional = new Profesional("asd", TipoUsuario.PROFESIONAL, "DSD",
                "dsadff", "eafef3", "fefef");

        PlanDeControl planDeControl = new PlanDeControl("gripe", 6);
        planDeControl.agregarTareasPROADM();
        planDeControl.verTareas();
    }
}