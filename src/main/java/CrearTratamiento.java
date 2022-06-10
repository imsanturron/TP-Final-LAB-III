import java.util.ArrayList;
import java.util.HashMap;

public interface CrearTratamiento {
    void crearTratamiento(HashMap<String, Paciente> pacs, ArrayList<PlanDeControl> planes);
}
