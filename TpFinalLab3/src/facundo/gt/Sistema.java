package facundo.gt;

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
        int  option = 0;

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
                        Administrador admin = (Administrador) us;
                        System.out.println("Bienvenido: "+ admin.getNombreCompleto());

                        do{
                            System.out.println("que desea hacer?");
                            System.out.println("0)salir");
                            System.out.println("1)agregar enfermedad");
                            System.out.println("2)asignar profesional");
                            System.out.println("3)regsitro de administradores");
                            System.out.println("4)crear tratamiento");
                            System.out.println("5)ingreso de paciente");
                            System.out.println("6)ingreso de profesional");
                            option = sc.nextInt();

                            switch (option) {
                                case 1: {
                                    admin.agregarEnfermedad(enfermedades);
                                    Persistencia.serializeArrayList(enfermedades,Archivos.ENFERMEDADESALL.getPath());
                                }
                                break;
                                case 2: {
                                    admin.asignarProfesional(profesionales);
                                    Persistencia.serializeHashMap(profesionales,Archivos.PROFESIONALESALL.getPath());
                                }
                                break;
                                case 3: {
                                    admin.registroAdministrador(administradores);
                                    Persistencia.serializeHashMap(administradores,Archivos.ADMINISTRADORESALL.getPath());

                                }
                                break;

                                case 4: {
                                    admin.crearTratamiento(pacientes, planesDeControl);
                                    Persistencia.serializeArrayList(planesDeControl,Archivos.PLANESPREDET.getPath());

                                }
                                break;
                                case 5: {
                                    admin.ingresoProfesional(profesionales);
                                    Persistencia.serializeHashMap(profesionales,Archivos.PROFESIONALESALL.getPath());
                                }
                                break;
                                case 6: {
                                    admin.ingresoPaciente(pacientes, profesionales);
                                    Persistencia.serializeHashMap(pacientes,Archivos.PACIENTESALL.getPath());
                                }
                                break;

                                default: {
                                    System.out.println("opcion inexistente");
                                }
                                break;
                            }

                        }while(option != 0);
                    }
                    break;
                    case PACIENTE: {
                        Paciente paciente = (Paciente) us;
                        System.out.println("Bienvenido: "+ paciente.getNombre());

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
                        do{
                            System.out.println("que desea hacer?");
                            System.out.println("0)salir");
                            System.out.println("1)completar tareas por hacer");
                            System.out.println("2)modificar tareas por hacer");
                            System.out.println("3)ver tareas por hacer");
                            option = sc.nextInt();
                            switch(option){
                                case 1:{
                                    paciente.completarTareasAHacer();
                                    Persistencia.serializeArrayList(planesDeControl,Archivos.PLANESPREDET.getPath());
                                }break;
                                case 2:{
                                    paciente.modificarTareasAHacer();
                                    Persistencia.serializeArrayList(planesDeControl,Archivos.PLANESPREDET.getPath());
                                }break;
                                case 3:{
                                    paciente.verTareasAHacer();
                                }break;

                                default:{
                                    System.out.println("opcion inexistente");
                                }break;
                            }
                        }while(option != 0);

                    }
                    break;
                    case PROFESIONAL: {
                        /***
                         *VerDatosPaciente(atributos, historial, etc)
                         *LlamadoAyudaSistema
                         * **/
                        Profesional profesional = (Profesional) us;
                        System.out.println("Bienvenido: "+ profesional.getNombreCompleto());
                        do{
                            System.out.println("que desea hacer?");
                            System.out.println("0)salir");
                            System.out.println("1)ver informaciones de tareas de paciente");
                            System.out.println("2)asignar plan");
                            System.out.println("3)ver ifnormacion de paciente en especifico");
                            System.out.println("4)ver nuevos pacientes");
                            System.out.println("5)extender plan");
                            System.out.println("6)finalizar plan");
                            option = sc.nextInt();
                            switch (option){
                                case 1:{
                                    profesional.infoAyerTareasPacientes();
                                }break;
                                case 2:{
                                    profesional.asignarPlan(pacientes,planesDeControl);
                                    Persistencia.serializeArrayList(planesDeControl,Archivos.PLANESPREDET.getPath());
                                    Persistencia.serializeHashMap(pacientes,Archivos.PACIENTESALL.getPath());
                                }break;
                                case 3:{
                                    profesional.VerInfoPacienteDeterminado();
                                }break;
                                case 4:{
                                    profesional.verNuevosPacientes(pacientes,planesDeControl);

                                }break;
                                case 5:{
                                    profesional.extenderPlan();
                                    Persistencia.serializeArrayList(planesDeControl,Archivos.PLANESPREDET.getPath());
                                }break;
                                case 6:{
                                    profesional.finalizarPlan();
                                    Persistencia.serializeArrayList(planesDeControl,Archivos.PLANESPREDET.getPath());

                                }break;

                                default:{
                                    System.out.println("opcion inexistente");
                                }break;
                            }
                        }while(option != 0);


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
