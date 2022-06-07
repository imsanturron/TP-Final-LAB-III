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
    ArrayList<Tarea> tareas = new ArrayList<>();
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

           try {
                switch (us.tipoUsuario) {
                    case ADMINISTRADOR: {
                        System.out.println("administrador");



                         /**AdministracionEnfermedades
                         *AdministracionPlanesDeControl
                         *DarDeBaja**/

                        Administrador admin = (Administrador) us;
                        String dni = admin.SeleccionDeProfesional(profesionales);
                        Profesional p = admin.buscarProfesional(profesionales,dni);
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
                        /***VerNuevosPacientes
                         *CrearPlanDeControl
                         *Asignar plan
                         *ModificarPlanExistente
                         *ControlPacientes
                         *FinalizaroExtender
                         *VerDatosPaciente(atributos, historial, etc)
                         *LlamadoAyudaSistema**/
                        Profesional profesional = (Profesional) us;
                        String dni = SeleccionDePacientePorAtender(profesional.getPacientesPorAtender(),profesional);


                    }
                    break;
                }
           }catch(NullPointerException npe){
                System.out.println("contraseña o usuario incorrectos desea volver a intentarlo?");
                rta = sc.next().charAt(0);
            }

            }while (rta == 's');


    }
    public String SeleccionDePacientePorAtender(HashSet<Paciente> pacientesPorAtender,Profesional profesional){
        String dni;
        System.out.println("digite el dni del paciente al cual se le aplicar el plan de control");
        for (Paciente pacientex : profesional.getPacientesPorAtender()) {
            System.out.println(pacientex.toString());
        }
        dni = sc.nextLine();
        return dni;
    }

}

