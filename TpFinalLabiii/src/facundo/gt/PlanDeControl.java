package facundo.gt;
import java.util.ArrayList;


public class PlanDeControl {
    private int id = 1;
    private String enfermedad;
    private int dias;
    private ArrayList<Tarea> tareas = new ArrayList<>();

    public PlanDeControl(String enfermedad, int dias) {
        this.id++;
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
}