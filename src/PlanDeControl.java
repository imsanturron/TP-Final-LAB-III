import java.util.ArrayList;
import java.util.Scanner;

public class PlanDeControl {
    private String enfermedad;
    private int dias;
    ArrayList<Realizar> tareas = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public PlanDeControl(String enfermedad, int dias) {
        this.enfermedad = enfermedad;
        this.dias = dias;
    }

    public void agregarTareas() {
        int opcion = 99;
        System.out.println("Que desea agregar?  1:Tarea de entrada numerica   ---    2:Tarea de entrada de texto");
        System.out.println("  3:Tarea de entrada 'si/no'   ---    2:Tarea de entrada tipo multiple choice");
        while (opcion != 0) {
            opcion = scan.nextInt();
            switch (opcion) {
                case 1:
                    tareas.add(new RNumerica("Tomarse temperatura", "7AM y 8PM"));
                    break;
                /*case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                */
                default:
                    break;
            }
        }
    }

    public void verTareas() {
        int i = 0;
        while (i < tareas.size()) {
            System.out.println(tareas.get(i).toString()); ///problema haciendolo con metodo
            i++;
        }
    }

    public void completarAcciones() {
        int i = 0;
        while (i < tareas.size()) {
            if (tareas.get(i) instanceof RNumerica)
                ((RNumerica) tareas.get(i)).ingresarNum();
            if (tareas.get(i) instanceof RTexto)
                ((RTexto) tareas.get(i)).ingresarString();
            if (tareas.get(i) instanceof RBooleana)
                ((RBooleana) tareas.get(i)).ingresarSN();
            if (tareas.get(i) instanceof RMulChoice)
                ((RMulChoice) tareas.get(i)).ingresarOpcionMultiple();
        }
    }

}
