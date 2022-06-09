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
                        //if(d.toDays() > )

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
