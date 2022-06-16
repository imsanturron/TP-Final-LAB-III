import java.time.LocalDate;
import java.util.*;

import static java.time.temporal.ChronoUnit.DAYS;

public class Sistema {
    HashMap<String, Paciente> pacientes = new HashMap<>();
    HashMap<String, Profesional> profesionales = new HashMap<>();
    HashMap<String, Administrador> administradores = new HashMap<>();
    HashMap<String, Usuario> usuariosDelSistema = new HashMap<>();
    ArrayList<PlanDeControl> planesDeControl = new ArrayList<>();
    ArrayList<String> enfermedades = new ArrayList<>();
    static Scanner sc = new Scanner(System.in); ///static

    public void menu() throws InputMismatchException {
        char rta = 'n';
        int option;

        do {
            usuariosDelSistema = Persistencia.DEserializeHashMap(Archivos.USUARIOSALL.getPath(), String.class, Usuario.class);
            planesDeControl = Persistencia.DEserializeArrayList(Archivos.PLANESPREDET.getPath(), PlanDeControl.class);
            enfermedades = Persistencia.DEserializeArrayList(Archivos.ENFERMEDADESALL.getPath(), String.class);
            String user, contrasena;
            System.out.println("Usuario(dni):");
            user = sc.nextLine();
            System.out.println("Contraseña:");
            contrasena = sc.nextLine();
            Login log = new Login(user, contrasena);
            Usuario us = log.inicioDeSesion(usuariosDelSistema);

            if (us != null) {

                if (us.getContrasena().equalsIgnoreCase(us.getDNI())) { ///primera vez que ingresa al sistema
                    System.out.println("Bienvenido al sistema de control!");
                    do {
                        System.out.println("Ingrese su nueva contraseña:");
                        String contra = sc.nextLine();
                        if (contra.length() < 4)
                            System.out.println("Su contraseña debe ser mayor a 3 caracteres");
                        else if (contra.equalsIgnoreCase(us.getDNI()))
                            System.out.println("Su contraseña no puede ser su DNI!");
                        else
                            us.setContrasena(contra);
                    } while (us.getContrasena().equalsIgnoreCase(us.getDNI()) || us.getContrasena().length() < 4);

                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                }

                switch (us.tipoUsuario) { ///despersistencia dependiendo del tipo de usuario
                    case ADMINISTRADOR: {
                        administradores = Persistencia.DEserializeHashMap(Archivos.ADMINISTRADORESALL.getPath(), String.class, Administrador.class);
                        pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath(), String.class, Paciente.class);
                        profesionales = Persistencia.DEserializeHashMap(Archivos.PROFESIONALESALL.getPath(), String.class, Profesional.class);
                        Administrador admin = new Administrador();

                        for (String clave : administradores.keySet()) {
                            if (us.getDNI().equals(clave)) {
                                admin = administradores.get(clave); ///hago referencia
                                admin.setContrasena(us.getContrasena());
                                Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());
                            }
                        }
                        System.out.println("Bienvenido " + admin.getNombreCompleto());
                        admin.verSugerenciasPlanes(pacientes, administradores, planesDeControl);
                        Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                        Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());

                        do {
                            System.out.println("\nQue desea hacer?");
                            System.out.println("0:Salir  ---  1:Ingreso de paciente  ---  2:Ingreso de profesional");
                            System.out.println("3:Registro de administradores  ---  4:Agregar enfermedad");
                            System.out.println("5:Crear plan predeterminado  ---  6:Modificar datos personales");
                            System.out.println("7:Asignar paciente conocido");
                            option = sc.nextInt();
                            sc.nextLine();

                            switch (option) {
                                case 0 -> {
                                    System.out.println("saliendo...");
                                    rta = 'n';
                                }
                                case 1 -> {
                                    admin.ingresoPaciente(pacientes, profesionales, usuariosDelSistema, enfermedades);
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                    Persistencia.serializeArrayList(enfermedades, Archivos.ENFERMEDADESALL.getPath());
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
                                    admin.crearTratamiento(pacientes, planesDeControl, enfermedades);
                                    Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeArrayList(enfermedades, Archivos.ENFERMEDADESALL.getPath());
                                }
                                case 6 -> {
                                    int opt;
                                    String cambiar;
                                    System.out.println("Que desea modificar?Ingrese numero:\n1:Nombre - 2:Contraseña - 3:Telefono");
                                    opt = sc.nextInt();
                                    sc.nextLine();

                                    if (opt == 1) {
                                        System.out.println("Ingrese su nuevo nombre completo:");
                                        cambiar = sc.nextLine();
                                        admin.setNombreCompleto(cambiar);
                                        us.setNombreCompleto(cambiar);
                                        System.out.println("Nombre asignado con exito");
                                    } else if (opt == 2) {
                                        System.out.println("ingrese su contraseña actual");
                                        cambiar = sc.nextLine();
                                        if (cambiar.equals(admin.getContrasena())) {
                                            System.out.println("Ingrese su nueva contraseña:");
                                            cambiar = sc.nextLine();
                                            admin.setContrasena(cambiar);
                                            us.setContrasena(cambiar);
                                            System.out.println("Contraseña nueva asignada con exito");
                                        } else
                                            System.out.println("Contraseña incorrecta");
                                    } else if (opt == 3) {
                                        System.out.println("Ingrese su nuevo telefono:");
                                        cambiar = sc.nextLine();
                                        admin.setTelefono(cambiar);
                                        us.setTelefono(cambiar);
                                        System.out.println("Telefono reasignado con exito");
                                    } else
                                        System.out.println("opcion incorrecta");

                                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                    Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());
                                }
                                case 7 -> {
                                    admin.ingresoPacienteConocido(pacientes, profesionales, enfermedades); ///paciente ya ingresado
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeArrayList(enfermedades, Archivos.ENFERMEDADESALL.getPath());
                                }
                                default -> System.out.println("opcion inexistente");
                            }
                        } while (option != 0);
                    }
                    break;

                    case PACIENTE: {
                        pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath(), String.class, Paciente.class);
                        profesionales = Persistencia.DEserializeHashMap(Archivos.PROFESIONALESALL.getPath(), String.class, Profesional.class);
                        Paciente paciente = new Paciente();
                        String savePass = "";
                        for (String clave : pacientes.keySet()) {
                            if (us.getDNI().equals(clave)) {
                                savePass = clave;
                                paciente = pacientes.get(clave);
                                paciente.setContrasena(us.getContrasena());
                                Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                            }
                        }

                        System.out.println("Bienvenido " + paciente.getNombreCompleto());
                        long diasEntre;

                        if (paciente.getfIni() != null) {
                            paciente.setfCompare(LocalDate.now());
                            diasEntre = DAYS.between(paciente.getfIni(), paciente.getfCompare()); //ver si paso un dia

                            if (diasEntre >= paciente.getComparadorFecha()) {///paso un dia o mas
                                paciente.getPlanDeControl().infoTareasDiaX();
                                paciente.persistirDia();
                                Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath(), String.class, Paciente.class);
                                paciente = pacientes.get(savePass);
                                paciente.resetDatosDiaYAlertar();

                                if (diasEntre == paciente.getComparadorFecha())
                                    paciente.setComparadorFecha(paciente.getComparadorFecha() + 1);
                                else
                                    paciente.setComparadorFecha((int) (paciente.getComparadorFecha() +
                                            (diasEntre - paciente.getComparadorFecha())) + 1);
                                ///no se contemplan absolutas posibilidades
                                Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                            }
                        }
                        if ((paciente.getfFin() == null || paciente.getfCompare() == null) || paciente.getfCompare().isAfter(paciente.getfFin())
                                || paciente.getfCompare().equals(paciente.getfFin())) {

                            System.out.println("Ha finalizado su plan");
                            paciente.resetPaciente();
                            Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                        } else {

                            do {
                                System.out.println("\nQue desea hacer?");
                                System.out.println("0)Salir");
                                System.out.println("1)Ver tareas a hacer");
                                System.out.println("2)Completar tareas a hacer");
                                System.out.println("3)Modificar tareas hechas");
                                System.out.println("4)Modificar datos personales");
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
                                    case 4 -> {
                                        int opt;
                                        String cambiar;
                                        System.out.println("Que desea modificar?Ingrese numero:\n1:Nombre - 2:Contraseña - 3:Telefono");
                                        opt = sc.nextInt();
                                        sc.nextLine();
                                        if (opt == 1) {
                                            System.out.println("Ingrese su nuevo nombre completo:");
                                            cambiar = sc.nextLine();
                                            paciente.setNombreCompleto(cambiar);
                                            us.setNombreCompleto(cambiar);
                                            System.out.println("Nombre asignado con exito");
                                        } else if (opt == 2) {
                                            System.out.println("ingrese su contraseña actual");
                                            cambiar = sc.nextLine();
                                            if (cambiar.equals(paciente.getContrasena())) {
                                                System.out.println("ingrese su nueva contraseña:");
                                                cambiar = sc.nextLine();
                                                paciente.setContrasena(cambiar);
                                                us.setContrasena(cambiar);
                                                System.out.println("contraseña nueva asignada con exito");
                                            } else
                                                System.out.println("Contraseña incorrecta");
                                        } else if (opt == 3) {
                                            System.out.println("ingrese su nuevo telefono:");
                                            cambiar = sc.nextLine();
                                            paciente.setTelefono(cambiar);
                                            us.setTelefono(cambiar);
                                            System.out.println("Telefono reasignado con exito");
                                        } else
                                            System.out.println("opcion incorrecta");

                                        Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                        Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    }
                                    default -> System.out.println("opcion inexistente");
                                }
                            } while (option != 0);
                        }
                    }
                    break;

                    case PROFESIONAL: {
                        administradores = Persistencia.DEserializeHashMap(Archivos.ADMINISTRADORESALL.getPath(), String.class, Administrador.class);
                        pacientes = Persistencia.DEserializeHashMap(Archivos.PACIENTESALL.getPath(), String.class, Paciente.class);
                        profesionales = Persistencia.DEserializeHashMap(Archivos.PROFESIONALESALL.getPath(), String.class, Profesional.class);

                        Profesional profesional = new Profesional();
                        for (String clave : profesionales.keySet()) {
                            if (us.getDNI().equals(clave)) {
                                profesional = profesionales.get(clave);
                                profesional.setContrasena(us.getContrasena());
                                Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                            }
                        }
                        System.out.println("Bienvenido " + profesional.getNombreCompleto());

                        profesional.AlertaNoRealizacionAyerPacientes(pacientes);
                        Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                        Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());

                        do {
                            System.out.println("\nQue desea hacer?");
                            System.out.println("0:Salir  ---  1:Ver informacion de tareas de ayer de los pacientes ");
                            System.out.println("2:Ver nuevos pacientes a atender  ---  3:Asignar planes");
                            System.out.println("4:Ver info paciente propio  ---  5:Extender plan  ---  6:Finalizar plan");
                            System.out.println("7:Pacientes vistos en espera  ---  8:Modificar datos personales");
                            System.out.println("9:Ver historial medico completo de paciente");
                            System.out.println("10:Sugerir al administrador plan para predeterminar");
                            option = sc.nextInt();
                            sc.nextLine();

                            switch (option) {
                                case 0 -> {
                                    System.out.println("saliendo...");
                                    rta = 'n';
                                }
                                case 1 -> {
                                    profesional.infoPacientesAyer(pacientes);
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                }
                                case 2 -> {
                                    profesional.verNuevosPacientes(pacientes, planesDeControl, enfermedades);
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                    Persistencia.serializeArrayList(enfermedades, Archivos.ENFERMEDADESALL.getPath());
                                }
                                case 3 -> {
                                    profesional.asignarPlan(pacientes, planesDeControl, enfermedades);
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                    Persistencia.serializeArrayList(enfermedades, Archivos.ENFERMEDADESALL.getPath());
                                }
                                case 4 -> {
                                    profesional.SeleccionDePacientePropioVerInfo();
                                }
                                case 5 -> {
                                    profesional.extenderPlan(pacientes);
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                }
                                case 6 -> {
                                    profesional.finalizarPlan(pacientes);
                                    Persistencia.serializeHashMap(pacientes, Archivos.PACIENTESALL.getPath());
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                    Persistencia.serializeArrayList(planesDeControl, Archivos.PLANESPREDET.getPath());
                                }
                                case 7 -> {
                                    profesional.pacientesVistosEnEspera();
                                }
                                case 8 -> {
                                    int opt;
                                    String cambiar;
                                    System.out.println("Que desea modificar?Ingrese numero:\n1:Nombre - 2:Contraseña - 3:Telefono");
                                    opt = sc.nextInt();
                                    sc.nextLine();

                                    if (opt == 1) {
                                        System.out.println("Ingrese su nuevo nombre completo:");
                                        cambiar = sc.nextLine();
                                        profesional.setNombreCompleto(cambiar);
                                        us.setNombreCompleto(cambiar);
                                        System.out.println("Nombre asignado con exito");
                                    } else if (opt == 2) {
                                        System.out.println("Ingrese su contraseña actual");
                                        cambiar = sc.nextLine();
                                        if (cambiar.equals(profesional.getContrasena())) {
                                            System.out.println("Ingrese su nueva contraseña:");
                                            cambiar = sc.nextLine();
                                            profesional.setContrasena(cambiar);
                                            us.setContrasena(cambiar);
                                            System.out.println("Contraseña nueva asignada con exito");
                                        } else
                                            System.out.println("Contraseña incorrecta");
                                    } else if (opt == 3) {
                                        System.out.println("Ingrese su nuevo telefono:");
                                        cambiar = sc.nextLine();
                                        profesional.setTelefono(cambiar);
                                        us.setTelefono(cambiar);
                                        System.out.println("Telefono reasignado con exito");
                                    } else
                                        System.out.println("opcion incorrecta");

                                    Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
                                    Persistencia.serializeHashMap(profesionales, Archivos.PROFESIONALESALL.getPath());
                                }
                                case 9 -> {
                                    profesional.verHistorialCompletoPaciente(pacientes);
                                }
                                case 10 -> {
                                    profesional.sugerirAdminPlan(administradores);
                                    Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());
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