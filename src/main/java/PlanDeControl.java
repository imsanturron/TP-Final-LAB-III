import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PlanDeControl {
    private String enfermedad;
    private int dias;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate hoy;
    private ArrayList<Realizar> tareas = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public PlanDeControl(String enfermedad, int dias) {
        this.enfermedad = enfermedad;
        this.dias = dias;
        hoy = LocalDate.now();
    }


    public void agregarTareasPROADM() {
        int opcion = 999;
        String accion, especificaciones;

        while (opcion != 0) {
            System.out.println("Que desea agregar? Agregue las tareas en orden de realizacion");
            System.out.println("  1:Tarea de entrada numerica    ---    2:Tarea binaria de entrada 'si/no'");
            System.out.println("  3:Tarea de entrada de texto    ---    4:Tarea de entrada tipo multiple choice    ----    0:Salir");
            opcion = scan.nextInt();
            scan.nextLine();

            switch (opcion) {
                case 0:
                    System.out.println("saliendo...");
                    break;
                case 1:
                    System.out.println("(NUMERICA)\nAccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("Especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RNumerica(accion, especificaciones));
                    break;
                case 2:
                    System.out.println("(SI/NO)\nAccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("Especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RBooleana(accion, especificaciones));
                    break;
                case 3:
                    System.out.println("(DE TEXTO)\nAccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("Especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RTexto(accion, especificaciones));
                    break;
                case 4:
                    System.out.println("(MULTIPLE CHOICE)\nAccion a realizar: ");
                    accion = scan.nextLine();
                    System.out.println("Especificaciones: (ej: horario, aclaraciones...)");
                    especificaciones = scan.nextLine();
                    tareas.add(new RMulChoice(accion, especificaciones));
                    break;
                default:
                    System.out.println("Numero invalido!");
                    break;
            }
        }
    }

    public PlanDeControl ModificarTareasYAsignarAuxPROADM(int dias) {
        PlanDeControl planaux = new PlanDeControl(enfermedad, dias);
        planaux.tareas.addAll(planaux.getTareas()); ///ver si se copian, o se pasan y borran
        char seguir = 's';
        int opcion;
        while (seguir == 's' || seguir == 'S') {
            System.out.println("Tareas, de la 1 a la x:");
            planaux.verTareas();

            System.out.println("Desea remover(1) o agregar(2)?");
            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion == 1) {
                System.out.println("Que numero de tarea desea borrar?");
                opcion = scan.nextInt();
                scan.nextLine();
                if (opcion <= planaux.getTareas().size() && opcion > 0) { ///me tira el array de una sin el get, como todo
                    System.out.println(planaux.getTareas().get(opcion - 1).getAccion() + "  ---> seguro que desea" +
                            "borrar esta tarea? s/n");
                    seguir = scan.next().charAt(0);
                    if (seguir == 's' || seguir == 'S')
                        planaux.getTareas().remove(opcion - 1);
                }
            } else if (opcion == 2) {
                planaux.agregarTareasPROADM();
            } else
                System.out.println("Numero invalido.");

            System.out.println("Desea seguir? s/n");
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
        char seguir = 's';
        int i = 0;
        while (i < tareas.size() && !tareas.get(i).isHecho() && (seguir == 's' || seguir == 'S')) { ///protected me sugiere atributo??????
            if (tareas.get(i) instanceof RNumerica)
                ((RNumerica) tareas.get(i)).ingresarNum();
            else if (tareas.get(i) instanceof RTexto)
                ((RTexto) tareas.get(i)).ingresarString();
            else if (tareas.get(i) instanceof RBooleana)
                ((RBooleana) tareas.get(i)).ingresarSN();
            else if (tareas.get(i) instanceof RMulChoice)
                ((RMulChoice) tareas.get(i)).ingresarOpcionMultiple();

            System.out.println("desea seguir ingresando?");
            seguir = scan.next().charAt(0);
            scan.nextLine();
        }
    }

    public void modificarAcciones() {
        int i = 0, opcion;
        char seguir = 's';
        System.out.println("Que accion desea modificar?");
        while (seguir == 's' || seguir == 'S') {
            while (i < tareas.size()) {
                System.out.println(i + ": " + tareas.get(i).getAccion());
                i++;
            }

            System.out.println("\nIngrese el numero de la accion:");
            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion < tareas.size()) {
                if (tareas.get(opcion) instanceof RNumerica)
                    ((RNumerica) tareas.get(opcion)).ingresarNum();
                else if (tareas.get(opcion) instanceof RTexto)
                    ((RTexto) tareas.get(opcion)).ingresarString();
                else if (tareas.get(opcion) instanceof RBooleana)
                    ((RBooleana) tareas.get(opcion)).ingresarSN();
                else if (tareas.get(opcion) instanceof RMulChoice)
                    ((RMulChoice) tareas.get(opcion)).ingresarOpcionMultiple();
            } else
                System.out.println("Numero no disponible!");

            System.out.println("Desea seguir modificando? s/n");
            seguir = scan.next().charAt(0);
            scan.nextLine();
        }
    }

    public boolean resetDia() {
        int i = 0, alerta = 0;
        hoy = LocalDate.now();

        while (i < tareas.size() && alerta != 1) {
            if (!tareas.get(i).isHecho())
                alerta = 1;
            else if (tareas.get(i) instanceof RNumerica) {
                tareas.get(i).setHecho(false);
                ((RNumerica) tareas.get(i)).setDato(-9999);
            } else if (tareas.get(i) instanceof RTexto) {
                tareas.get(i).setHecho(false);
                ((RTexto) tareas.get(i)).setDato("");
            } else if (tareas.get(i) instanceof RBooleana) {
                tareas.get(i).setHecho(false);
                ((RBooleana) tareas.get(i)).setDato(false);
            } else if (tareas.get(i) instanceof RMulChoice) {
                tareas.get(i).setHecho(false);
                ((RMulChoice) tareas.get(i)).setDatOpcion("sin ingresar");
            }
        }
        if (alerta == 1) {
            System.out.println("No ingresaste todas tus actividades del dia de ayer. Intenta" +
                    "realizar tus actividades correspondientes por favor.");
            return true;
        }
        return false;
    }

    public void infoTareasDiaX() {
        int i = 0;
        System.out.println(hoy);
        while (i < tareas.size()) {
            if (!tareas.get(i).isHecho())
                System.out.println(tareas.get(i).getAccion() + ": !-tarea sin realizar-!");
            else if (tareas.get(i) instanceof RNumerica)
                System.out.println(tareas.get(i).getAccion() + ": " + ((RNumerica) tareas.get(i)).getDato());
            else if (tareas.get(i) instanceof RTexto)
                System.out.println(tareas.get(i).getAccion() + ": " + ((RTexto) tareas.get(i)).getDato());
            else if (tareas.get(i) instanceof RBooleana)
                System.out.println(tareas.get(i).getAccion() + ": " + ((RBooleana) tareas.get(i)).isDato());
            else if (tareas.get(i) instanceof RMulChoice)
                System.out.println(tareas.get(i).getAccion() + ": " + ((RMulChoice) tareas.get(i)).getDatOpcion());
        }
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public ArrayList<Realizar> getTareas() {
        return tareas;
    }

    public void setTareas(ArrayList<Realizar> tareas) {
        this.tareas = tareas;
    }

    public int getDias() {
        return dias;
    }

    public LocalDate getHoy() {
        return hoy;
    }

    public void setHoy(LocalDate hoy) {
        this.hoy = hoy;
    }
}
