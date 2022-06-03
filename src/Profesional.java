import java.util.*;

public class Profesional extends Usuario {
    /* *fechas
  -VerNuevosPacientes
  -CrearPlanDeControl
  -Asignar plan
  -ModificarPlanExistente
  -ControlPacientes
  -FinalizaroExtender
  -VerDatosPaciente(atributos, historial, etc)*/
    private UUID matricula;
    Scanner scan = new Scanner(System.in);
    HashSet<Paciente> pacientesAAtender = new HashSet<>();

    public Profesional(String nombreCompleto, TipoUsuario tipoUsuario, String DNI,
                       String contrasena, String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        matricula = UUID.randomUUID();
    }

    public void verNuevosPacientes(HashMap<String, Paciente> pacientes) {
        int nuevos = 0;
        for (Paciente pacientex : pacientes.values()) {
            if (!pacientex.isAtendido() && matricula.equals(pacientex.getMatriculaMedico())) {
                pacientesAAtender.add(pacientex);
                nuevos++;
            }
        }
        System.out.println("tienes " + nuevos + " nuevos pacientes.\ndeseas asignarles planes ahora? s/n");
        try {
            char opcion = scan.next().charAt(0);
            if (opcion == 'S' || opcion == 's')
                asignarPlan();
        } catch (InputMismatchException e) {
            System.out.println("debiste ingresar un caracter");
        }
    }

    public void asignarPlan() {
        int opcion;
        List<Paciente> listaConver = new ArrayList<>(pacientesAAtender);
        System.out.println(listaConver);
        System.out.println(pacientesAAtender);///revisar
        while (!pacientesAAtender.isEmpty()) {
            System.out.println(listaConver.get(0).nombreCompleto + "  enferemdad:" + listaConver.get(0).getEnfermedad());

            System.out.println("que desea? 1:Asignar plan predeterminado 2:Modificar plan predeterminado" +
                    "3: Crear plan nuevo 0:Salir");
            opcion = scan.nextInt();
            scan.nextLine();

            // switch (opcion){
            //   1:
            // }
        }
        System.out.println("No tienes pacientes nuevos!");
    }

    public UUID getMatricula() {
        return matricula;
    }

    public String getEdad() {
        return edad;
    }


}
