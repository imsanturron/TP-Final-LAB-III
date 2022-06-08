package facundo.gt;

import java.time.LocalDate;
import java.util.*;

import static facundo.gt.Sistema.sc;

public class Profesional extends Usuario {

    private UUID matricula;
    Scanner scan = new Scanner(System.in);
    private PlanDeControl p;
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

    public String SeleccionDePacientePorAtender(){
        String dni;
        System.out.println("digite el dni del paciente al cual se le aplicar el plan de control");
        for (Paciente pacientex :pacientesPorAtender) {
            System.out.println(pacientex.toString());
        }
        dni = sc.nextLine();
        return dni;
    }
    public Paciente BuscarPacientePorDni(String dni){
        Paciente p = null;
        for (Paciente pacientex : pacientesPorAtender) {
            if(pacientex.equals(dni)){
                p = pacientex;
                break;
            }
        }
        return p;
    }

    public void verPlanesDeControl(ArrayList<PlanDeControl>planesDeControl,String enfermedad){
        p.verPlanesExistente(planesDeControl,enfermedad);
    }
    //public void asginarPlan()




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