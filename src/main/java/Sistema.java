import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Sistema {

    HashMap<String, Paciente> pacientes = new HashMap<>();
    HashMap<String, Profesional> profesionales = new HashMap<>();
    HashMap<String, Administrador> administradores = new HashMap<>();
    HashMap<String, Usuario> usuariosDelSistema = new HashMap<>();
    ArrayList<PlanDeControl> planesDeControl = new ArrayList<>();
    ArrayList<String> enfermedades = new ArrayList<>();
    static Scanner sc = new Scanner(System.in); ///static
    public void menu() {
        char rta = 'n';

        do {
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


            //serializacion y deserializacion testeada.
            String user, contrasena;
            System.out.println("usuario: ");
            user = sc.nextLine();
            System.out.println("contraseña: ");
            contrasena = sc.nextLine();
            Login log = new Login(user, contrasena);
            Usuario us = log.inicioDeSesion(usuariosDelSistema);

            if (us != null) {
                switch (us.tipoUsuario) {
                    case ADMINISTRADOR: {
                        System.out.println("administrador");
                        Administrador admin = (Administrador) us;

                        ///menu

                        /**AdministracionEnfermedades
                         *AdministracionPlanesDeControl
                         *DarDeBaja
                         * */
                    }
                    break;
                    case PACIENTE: {
                        System.out.println("paciente");
                        Paciente paciente = (Paciente) us;
                        paciente.setfCompare(LocalDate.now());
                        Duration d = Duration.between(paciente.getfIni(), paciente.getfCompare());

                        if(d.toDays() == paciente.getComparadorFecha()) {
                            boolean opt = paciente.getPlanDeControl().resetDia();
                            paciente.setAlertaDeNoRealizacion(opt);
                            paciente.setComparadorFecha(paciente.getComparadorFecha() + 1);
                        }
                        if(paciente.getfCompare() == paciente.getfFin()){
                            System.out.println("ha finalizado su plan");
                            paciente.resetPaciente();
                        }
                      ///menu

                    }
                    break;
                    case PROFESIONAL: {
                        System.out.println("profesional");
                        /***
                         *CrearPlanDeControl
                         *ModificarPlanExistente
                         *ControlPacientes
                         *FinalizaroExtender
                         *VerDatosPaciente(atributos, historial, etc)
                         *LlamadoAyudaSistema
                         * **/
                        Profesional profesional = (Profesional) us;
                        profesional.infoAyerTareasPacientes();

                        ///menu
                    }
                    break;
                }
            } else {
                System.out.println("usuario o contraseña incorrecta, desea volver a intentarlo?(s/n)");
                rta = sc.next().charAt(0);
                sc.nextLine();
            }
        } while (rta == 's');
    }
}
