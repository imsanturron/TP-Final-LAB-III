import java.util.UUID;

public class Paciente extends Usuario {
    /*fechas
*Profesional
*Atendido
*PlanDeControl
*HistorialMedico
-VerAccionesDeHoy
-CompletarAccionesDeHoy(y modificar)*/
    private Profesional profesionalPropio;
    private boolean atendido = false;
    private String enfermedad;
    private PlanDeControl planDeControl;
    /// HistorialMedico


    public Paciente(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                    String telefono, String enfermedad, Profesional profesionalPropio, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        this.profesionalPropio = profesionalPropio;
        this.enfermedad = enfermedad;
    }

    public UUID getMatriculaMedico(){
        return profesionalPropio.getMatricula();
    }

    public boolean isAtendido() {
        return atendido;
    }

    public String getEnfermedad() {
        return enfermedad;
    }


}
