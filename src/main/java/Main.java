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
            Paciente pacso = new Paciente("paciente juan", TipoUsuario.PACIENTE, "999",
                    "DAFF", "DASD", "gripe", x, "44");
            pacso.setfFin(LocalDate.now());
            pacso.setfFin(LocalDate.now().plusDays(88));
            pacso.setfIni(LocalDate.now().plusDays(2));
            profesionalprueba.getPacientesACargo().add(pacso);
            profesionales.put(profesionalprueba.getDNI(), profesionalprueba);
            Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
         */

        /*
        Administrador admmm = new Administrador("admin master", TipoUsuario.ADMINISTRADOR, "9999", "10", "0", "0");
            usuariosDelSistema.put("9999", admmm);
            administradores.put("9999", admmm);
            Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
            Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());
         */
    }
}