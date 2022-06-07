import java.time.LocalDate;
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
    private LocalDate fIni;
    private LocalDate fFin;
    private boolean alertaDeNoRealizacion = false;
    /// HistorialMedico


    public Paciente(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                    String telefono, String enfermedad, Profesional profesionalPropio, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        this.profesionalPropio = profesionalPropio;
        this.enfermedad = enfermedad;
    }

    public void resetDatosDiaYAlertar() {
        alertaDeNoRealizacion = planDeControl.resetDia();
    }


    public void verTareasAHacer() {
        planDeControl.verTareas();
    }

    public void completarTareasAHacer() {
        planDeControl.completarAcciones();
    }

    public void modificarTareasAHacer() {
        planDeControl.modificarAcciones();
    }

    public UUID getMatriculaMedico() {
        return profesionalPropio.getMatricula();
    }

    public boolean isAtendido() {
        return atendido;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public String getDNI() {
        return DNI;
    }

    public void setPlanDeControl(PlanDeControl planDeControl) {
        this.planDeControl = planDeControl;
    }

    public LocalDate getfIni() {
        return fIni;
    }

    public void setfIni(LocalDate fIni) {
        this.fIni = fIni;
    }

    public LocalDate getfFin() {
        return fFin;
    }

    public void setfFin(LocalDate fFin) {
        this.fFin = fFin;
    }

    public boolean isAlertaDeNoRealizacion() {
        return alertaDeNoRealizacion;
    }

    public String getNombre(){
        return nombreCompleto;
    }
}