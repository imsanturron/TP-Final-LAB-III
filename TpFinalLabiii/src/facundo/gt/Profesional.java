package facundo.gt;

import java.time.LocalDate;
import java.util.*;

public class Profesional extends Usuario {

    private UUID matricula;
    Scanner scan = new Scanner(System.in);
    HashSet<Paciente> pacientesPorAtender = new HashSet<>();

    public Profesional(String nombreCompleto, TipoUsuario tipoUsuario, String DNI,
                       String contrasena, String telefono, String edad) {
        super(nombreCompleto, tipoUsuario, DNI, contrasena, telefono, edad,true);
        this.matricula = UUID.randomUUID();
    }

    public void setPaciente(Paciente p){
        pacientesPorAtender.add(p);
    }


    public HashSet<Paciente> getPacientesPorAtender() {
        return pacientesPorAtender;
    }

    public UUID getMatricula() {
        return matricula;
    }

    public String getEdad() {
        return edad;
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
        return "Profesional{" +
                "nombreCompleto='" + nombreCompleto + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", DNI='" + DNI + '\'' +
                ", edad='" + edad + '\'' +
                ", contraseña='" + contraseña + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}