package facundo.gt;
import java.util.ArrayList;
import java.util.Objects;


public class PlanDeControl {
    private String enfermedad;
    private int dias;
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public PlanDeControl(String enfermedad, int dias) {
        this.enfermedad = enfermedad;
        this.dias = dias;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public ArrayList<Tarea> getArrayTareas() {
        return tareas;
    }

    public int getDias() {
        return dias;
    }

    public void verPlanesExistente(ArrayList<PlanDeControl>planesDeControl,String enfermedad){
        for(PlanDeControl p : planesDeControl){
            if(p.getEnfermedad().equals(enfermedad)){
                System.out.println(p.toString());
            }
        }
    }

    public void asignarPlanPredeterminado(ArrayList<PlanDeControl>planesDeControl,Paciente p){
        for(PlanDeControl plan : planesDeControl){
            if(plan.getEnfermedad().equals(p.getEnfermedad())){
                p.setPlanDeControl(plan);
                break;
            }
        }
    }





    @Override
    public String toString() {
        return "PlanDeControl{" +
                ", enfermedad='" + enfermedad + '\'' +
                ", dias=" + dias +
                ", tareas=" + tareas +
                '}';
    }
}
