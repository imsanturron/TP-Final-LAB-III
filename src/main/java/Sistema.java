import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.desktop.ScreenSleepEvent;
import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

public class Sistema {

    HashMap<Integer, Paciente> pacientes = new HashMap<>();
    HashMap<Integer, Profesional> profesionales = new HashMap<>();
    HashMap<String, Administrador> administradores = new HashMap<>();
    HashMap<Integer, Usuario> usuariosDelSistema = new HashMap<>();
    ArrayList<PlanDeControl> PlanesDeControl = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);


    public void menu() {
        char rta = 'n';

        do {

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
            }

        } while (rta == 's');


    }

}
