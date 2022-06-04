import java.util.ArrayList;
import java.util.Scanner;

public class PlanDeControl {
    private String enfermedad;
    private int dias;
    private ArrayList<Realizar> tareas = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public PlanDeControl(String enfermedad, int dias) {
        this.enfermedad = enfermedad;
        this.dias = dias;
    }

    public void agregarTareasPROADM() {
        int opcion = 999;
        String accion, especificaciones;

        while (opcion != 0) {
            System.out.println("Que desea agregar?\n  1:Tarea de entrada numerica    ---    2:Tarea de entrada de texto    ");
            System.out.println("---    3:Tarea de entrada 'si/no'    ---    2:Tarea de entrada tipo multiple choice");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("(NUMERICA)\naccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RNumerica(accion, especificaciones));
                    break;
                case 2:
                    System.out.println("(SI/NO)\naccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RBooleana(accion, especificaciones));
                    break;
                case 3:
                    System.out.println("(DE TEXTO)\naccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RTexto(accion, especificaciones));
                    break;
                case 4:
                    System.out.println("(MULTIPLE CHOICE)\naccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RMulChoice(accion, especificaciones));
                    break;
                default:
                    System.out.println("numero invalido!");
                    break;
            }
        }
    }

    public PlanDeControl ModificarTareasYAsignarAuxPROADM(int dias) {
        PlanDeControl planaux = new PlanDeControl(enfermedad, dias);
        planaux.tareas.addAll(planaux.getArrayTareas()); ///ver si se copian, o se pasan y borran
        char seguir = 's';
        int opcion = 0;


        while (seguir == 's' || seguir == 'S') {
            System.out.println("Tareas, de la 1 a la x:");
            planaux.verTareas();

            System.out.println("desea remover(1) o agregar(2)?");
            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion == 1) {
                System.out.println("que numero de tarea desea borrar?");
                opcion = scan.nextInt();
                scan.nextLine();
                if (opcion <= planaux.getArrayTareas().size() && opcion > 0) { ///me tira el array de una sin el get, como todo
                    System.out.println(planaux.getArrayTareas().get(opcion - 1).getAccion() + "  ---> seguro que desea" +
                            "borrar esta tarea? s/n");
                    seguir = scan.next().charAt(0);
                    if (seguir == 's' || seguir == 'S')
                        planaux.getArrayTareas().remove(opcion - 1);
                }
            } else if (opcion == 2) {
                planaux.agregarTareasPROADM();
            } else
                System.out.println("Numero invalido.");

            System.out.println("desea seguir? s/n");
        }
        return planaux;
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
        while (i < tareas.size() && !tareas.get(i).isHecho()) { ///protected me sugiere atributo??????
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

    public void modificarAcciones() {
        int i = 0, opcion;
        String seguir = "si";
        System.out.println("que accion desea modificar? ingrese el numero de la accion");
        while (seguir.equalsIgnoreCase("si")) {
            while (i < tareas.size()) {
                System.out.println(i + ": " + tareas.get(i).getAccion());
                i++;
            }
            opcion = scan.nextInt();
            if (opcion < tareas.size()) {
                if (tareas.get(opcion) instanceof RNumerica)
                    ((RNumerica) tareas.get(opcion)).ingresarNum();
                if (tareas.get(opcion) instanceof RTexto)
                    ((RTexto) tareas.get(opcion)).ingresarString();
                if (tareas.get(opcion) instanceof RBooleana)
                    ((RBooleana) tareas.get(opcion)).ingresarSN();
                if (tareas.get(opcion) instanceof RMulChoice)
                    ((RMulChoice) tareas.get(opcion)).ingresarOpcionMultiple();
            } else
                System.out.println("numero no disponible!");

            scan.nextLine();

            System.out.println("desea seguir modificando? 'si' si desea seguir");
            seguir = scan.nextLine();
        }
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public ArrayList<Realizar> getArrayTareas() {
        return tareas;
    }
}
