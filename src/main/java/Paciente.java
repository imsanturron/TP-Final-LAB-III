import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.ArrayList;

public class Paciente extends Usuario {
    private Profesional profesionalPropio;
    private boolean atendido = false;
    private boolean visto = false;
    private String enfermedad;
    private PlanDeControl planDeControl; ///clonar

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fIni;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate fCompare;

    private int comparadorFecha;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate fFin;

    private boolean alertaDeNoRealizacion = false;
    ArrayList<PlanDeControl> historialMedico = new ArrayList<>();

    public Paciente(){
        super();
    }

    public Paciente(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                    String telefono, String enfermedad, Profesional profesionalPropio, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad);
        this.profesionalPropio = profesionalPropio;
        this.enfermedad = enfermedad;
    }

    public void persistirDia(){
        historialMedico.add(planDeControl); ///persistir paciente entero o como
    }
    public void resetDatosDiaYAlertar() {
        alertaDeNoRealizacion = planDeControl.resetDia();
    }


    public void verTareasAHacer() {
        if (planDeControl != null)
            planDeControl.verTareas();
        else
            System.out.println("Usted ya ha finalizado su plan!");
    }

    public void completarTareasAHacer() {
        if (planDeControl != null)
            planDeControl.completarAcciones();
        else
            System.out.println("Usted ya ha finalizado su plan!");
    }

    public void modificarTareasAHacer() {
        if (planDeControl != null)
            planDeControl.modificarAcciones();
        else
            System.out.println("Usted ya ha finalizado su plan!");
    }

    public void resetPaciente() {
    this.fCompare = null;
    fFin = null;
    this.fIni = null;
    this.atendido  = false;
    this.enfermedad = "";
    this.planDeControl = null;
    this.alertaDeNoRealizacion = false;
    this.comparadorFecha = 0;
    //this.profesionalPropio = null;
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

    public Profesional getProfesionalPropio() {
        return profesionalPropio;
    }

    public void setProfesionalPropio(Profesional profesionalPropio) {
        this.profesionalPropio = profesionalPropio;
    }

    public int getComparadorFecha() {
        return comparadorFecha;
    }

    public void setComparadorFecha(int comparadorFecha) {
        this.comparadorFecha = comparadorFecha;
    }

    public void setAlertaDeNoRealizacion(boolean alertaDeNoRealizacion) {
        this.alertaDeNoRealizacion = alertaDeNoRealizacion;
    }

    public void setAtendido(boolean atendido) {
        this.atendido = atendido;
    }

    public ArrayList<PlanDeControl> getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(ArrayList<PlanDeControl> historialMedico) {
        this.historialMedico = historialMedico;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
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