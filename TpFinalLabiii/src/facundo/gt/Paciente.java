package facundo.gt;

import java.time.LocalDate;
import java.util.UUID;

public class Paciente extends Usuario {

    private Profesional profesionalPropio;
    private boolean atendido = false;
    private String enfermedad;
    private PlanDeControl planDeControl;



    public Paciente(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                    String telefono, String enfermedad, Profesional profesionalPropio, String edad,boolean atendido) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad,true);
        this.profesionalPropio = profesionalPropio;
        this.enfermedad = enfermedad;
        this.atendido = atendido;
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

    public String getDNI(){
        return DNI;
    }

    public void setPlanDeControl(PlanDeControl planDeControl) {
        this.planDeControl = planDeControl;
    }


    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", DNI='" + DNI + '\'' +
                ", edad='" + edad + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}