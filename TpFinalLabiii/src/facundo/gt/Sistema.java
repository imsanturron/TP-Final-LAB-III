package facundo.gt;

import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.desktop.ScreenSleepEvent;
import java.lang.reflect.Array;
import java.util.*;

public class Sistema {
    HashMap<Integer, Paciente> pacientes = new HashMap<>();
    HashMap<Integer, Profesional> profesionales = new HashMap<>();
    HashMap<String, Administrador> administradores = new HashMap<>();
    HashMap<Integer,Usuario> usuariosDelSistema = new HashMap<>();
    ArrayList<PlanDeControl> PlanesDeControl = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);


    public void menu() {
        char rta = 'n';

       do {

           String user,contraseña;
           System.out.println("usuario: ");
           user = sc.nextLine();
           System.out.println("contraseña: ");
           contraseña = sc.nextLine();
           Login log = new Login(user,contraseña);
           Usuario us = log.inicioDeSesion(usuariosDelSistema);

           if(us != null) {
               switch (us.tipoUsuario) {
                   case ADMINISTRADOR: {
                       System.out.println("administrador");


                       /**AdministracionEnfermedades
                        *AdministracionPlanesDeControl
                        *DarDeBaja**/

                       Administrador admin = (Administrador) us;
                       String dni = admin.SeleccionDeProfesional(profesionales);
                       Profesional p = admin.buscarProfesional(profesionales, dni);
                       admin.ingresoPaciente(p);
                       admin.ingresoProfesional(profesionales);
                       admin.registroAdministrador(administradores);


                   }
                   break;

                   case PACIENTE: {
                       System.out.println("paciente");
                       /***VerAccionesDeHoy
                        *CompletarAccionesDeHoy
                        *ModificarAccionesDeHoy**/
                       Paciente paciente = (Paciente) us;

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
                        *LlamadoAyudaSistema**/
                       Profesional profesional = (Profesional) us;
                       String dni = profesional.SeleccionDePacientePorAtender();
                       Paciente p = profesional.BuscarPacientePorDni(dni);
                       profesional.verPlanesDeControl(PlanesDeControl, p.getEnfermedad());


                   }
                   break;
               }
           }
           else{
               System.out.println("usuario o contraseña incorrecta, desea volver a intentarlo?(s/n)");
               rta = sc.next().charAt(0);
           }

            }while (rta == 's');


    }

}

