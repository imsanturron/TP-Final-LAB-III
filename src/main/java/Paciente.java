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
    private LocalDate fCompare;
    private int comparadorFecha;
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

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public void verTareasAHacer() {
        if (planDeControl != null)
            planDeControl.verTareas();
        else
            System.out.println("Usted ya ha finzalizado su plan!");
    }

    public void completarTareasAHacer() {
        if (planDeControl != null)
            planDeControl.completarAcciones();
        else
            System.out.println("Usted ya ha finzalizado su plan!");
    }

    public void modificarTareasAHacer() {
        if (planDeControl != null)
            planDeControl.modificarAcciones();
        else
            System.out.println("Usted ya ha finzalizado su plan!");
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

    public String getNombre() {
        return nombreCompleto;
    }

    public LocalDate getfCompare() {
        return fCompare;
    }

    public void setfCompare(LocalDate fCompare) {
        this.fCompare = fCompare;
    }

    public PlanDeControl getPlanDeControl() {
        return planDeControl;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "enfermedad='" + enfermedad + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", DNI='" + DNI + '\'' +
                '}';
    }
}