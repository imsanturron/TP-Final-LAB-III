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
        int option;

        do {
            //serializacion y deserializacion testeada.
            usuariosDelSistema = Persistencia.DEserializeHashMap(Archivos.USUARIOSALL.getPath());
            planesDeControl = Persistencia.DEserializeArrayList(Archivos.PLANESPREDET.getPath());
            enfermedades = Persistencia.DEserializeArrayList(Archivos.ENFERMEDADESALL.getPath());
            String user, contrasena;
            System.out.println("usuario(dni): ");
            user = sc.nextLine();
            System.out.println("contraseña: ");
            contrasena = sc.nextLine();
            Login log = new Login(user, contrasena);
            Usuario us = log.inicioDeSesion(usuariosDelSistema);

            if (us != null) {

                if (us.getContrasena().equalsIgnoreCase(us.getDNI())) {
                    System.out.println("Bienvenido al sistema de control!");
                    do {
                        System.out.println("Ingrese su nueva contraseña:");
                        String contra = sc.nextLine();
                        if (contra.length() < 4)
                            System.out.println("Su contraseña debe ser mayor a 3 caracteres");
                        else if (us.getContrasena().equalsIgnoreCase(us.getDNI()))
                            System.out.println("Su contraseña no puede ser su DNI!");
                        else
                            us.setContrasena(contra);
                    } while (!us.getContrasena().equalsIgnoreCase(us.getDNI()) && us.getContrasena().length() > 3);
                }

                switch (us.tipoUsuario) {
                    case ADMINISTRADOR: {
                        administradores = Persistencia.DEserializeHashMap(Archivos.ADMINISTRADORESALL.getPath());
                        pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath());
                        profesionales = Persistencia.DEserializeHashMap(Archivos.PROFESIONALESALL.getPath());
                        System.out.println("Administrador");
                        Administrador admin = (Administrador) us;
                        for (String clave : administradores.keySet()) {
                            if (admin.getDNI().equals(clave))
                                admin = administradores.get(clave);
                        }
                        System.out.println("Bienvenido " + admin.getNombreCompleto());

                        do {
                            System.out.println("que desea hacer?");
                            System.out.println("0:Salir  ---  1:Ingreso de paciente  ---  2:Ingreso de profesional");
                            System.out.println("3:Regsitro de administradores  ---  4:Agregar enfermedad");
                            System.out.println("5:Crear plan predeterminado  ---  6:Ingreso de profesional");
                            option = sc.nextInt();
                            sc.nextLine();

                            switch (option) {
                                case 0 -> {
                                    System.out.println("saliendo...");
                                    rta = 'n';
                                }
                                case 1 -> {
                                    admin.ingresoPaciente(pacientes, profesionales, usuariosDelSistema);
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                }
                                case 2 -> {
                                    admin.ingresoProfesional(profesionales, usuariosDelSistema);
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                }
                                case 3 -> {
                                    admin.registroAdministrador(administradores, usuariosDelSistema);
                                    Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());
                                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                }
                                case 4 -> {
                                    admin.agregarEnfermedad(enfermedades);
                                    Persistencia.serializeArrayList(enfermedades, Archivos.ENFERMEDADESALL.getPath());
                                }
                                case 5 -> {
                                    admin.crearTratamiento(pacientes, planesDeControl);
                                    Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                }
                                default -> System.out.println("opcion inexistente");
                            }
                        } while (option != 0);
                        /**
                         *DarDeBaja
                         * */
                    }
                    break;

                    case PACIENTE: {
                        pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath());
                        profesionales = Persistencia.DEserializeHashMap(Archivos.PROFESIONALESALL.getPath());
                        System.out.println("Paciente");
                        Paciente paciente = (Paciente) us;
                        for (String clave : pacientes.keySet()) {
                            if (paciente.getDNI().equals(clave))
                                paciente = pacientes.get(clave);
                        }

                        if (paciente.getfIni() != null) {
                            paciente.setfCompare(LocalDate.now());
                            Duration d = Duration.between(paciente.getfIni(), paciente.getfCompare());

                            if (d.toDays() == paciente.getComparadorFecha()) {
                                paciente.getPlanDeControl().infoTareasDiaX();
                                paciente.persistirDia();
                                Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                paciente.resetDatosDiaYAlertar();
                                paciente.setComparadorFecha(paciente.getComparadorFecha() + 1);
                            }
                        }
                        if (paciente.getfCompare() == paciente.getfFin() || (paciente.getfFin() == null || paciente.getfCompare() == null)) {
                            System.out.println("Ha finalizado su plan");
                            paciente.resetPaciente();
                        } else {

                            do {
                                System.out.println("que desea hacer?");
                                System.out.println("0)Salir");
                                System.out.println("1)Ver tareas a hacer");
                                System.out.println("2)Completar tareas a hacer");
                                System.out.println("3)Modificar tareas hechas");
                                option = sc.nextInt();
                                sc.nextLine();
                                switch (option) {
                                    case 0 -> {
                                        System.out.println("saliendo...");
                                        rta = 'n';
                                    }
                                    case 1 -> {
                                        paciente.verTareasAHacer();
                                    }
                                    case 2 -> {
                                        paciente.completarTareasAHacer();
                                        Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                        Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    }
                                    case 3 -> {
                                        paciente.modificarTareasAHacer();
                                        Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                        Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    }
                                    default -> System.out.println("opcion inexistente");
                                }
                            } while (option != 0);
                        }
                    }
                    break;

                    case PROFESIONAL: {
                        administradores = Persistencia.DEserializeHashMap(Archivos.ADMINISTRADORESALL.getPath());
                        pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath());
                        profesionales = Persistencia.DEserializeHashMap(Archivos.PROFESIONALESALL.getPath());
                        System.out.println("profesional");
                        /***
                         *ControlPacientes
                         *VerDatosPaciente(atributos, historial, etc)
                         *LlamadoAyudaSistema
                         * **/
                        Profesional profesional = (Profesional) us;
                        for (String clave : profesionales.keySet()) {
                            if (profesional.getDNI().equals(clave))
                                profesional = profesionales.get(clave);
                        }
                        profesional.AlertaNoRealizacionAyerPacientes();

                        do {
                            System.out.println("que desea hacer?");
                            System.out.println("0:Salir  ---  1:ver informaciones de tareas de ayer de los pacientes ");
                            System.out.println("2:ver nuevos pacientes a atender  ---  3:asignar planes");
                            System.out.println("4:Seleccionar paciente a atender  ---  5:extender plan  ---  6:finalizar plan");
                            option = sc.nextInt();
                            sc.nextLine();
                            switch (option) {
                                case 0 -> {
                                    System.out.println("saliendo...");
                                    rta = 'n';
                                }
                                case 1 -> {
                                    profesional.infoPacientesAyer();
                                }
                                case 2 -> {
                                    profesional.verNuevosPacientes(pacientes, planesDeControl);
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    //esto me serializa arrays dentro de profesional?
                                }
                                case 3 -> {
                                    profesional.asignarPlan(pacientes, planesDeControl);
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                }
                                case 4 -> {
                                    profesional.SeleccionDePacientePorAtender();
                                }
                                case 5 -> {
                                    profesional.extenderPlan();
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                }
                                case 6 -> {
                                    profesional.finalizarPlan();
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                }
                                default -> System.out.println("opcion inexistente");
                            }
                        } while (option != 0);
                    }
                    break;
                }
            } else {
                System.out.println("Usuario o contraseña incorrecta, desea volver a intentarlo?(s/n)");
                rta = sc.next().charAt(0);
                sc.nextLine();
            }
        } while (rta == 's' || rta == 'S');
    }
}
