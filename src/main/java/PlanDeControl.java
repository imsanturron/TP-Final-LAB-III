import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PlanDeControl implements Cloneable {
    private String enfermedad;
    private int dias;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate hoy;
    private ArrayList<Realizar> tareas = new ArrayList<>();
    private Scanner scan = new Scanner(System.in);

    public PlanDeControl() {
    }

    public PlanDeControl(String enfermedad, int dias) {
        this.enfermedad = enfermedad;
        this.dias = dias;
        hoy = LocalDate.now();
    }


    public void agregarTareasPROADM() {
        int opcion = 999;
        String accion, especificaciones;

        while (opcion != 0) {
            System.out.println("Que tareas desea agregar al plan? Agregue las tareas en orden de realizacion");
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
        planaux.getTareas().addAll(tareas);
        char seguir = 's';
        int opcion;

        while (seguir == 's' || seguir == 'S') {
            System.out.println("Tareas, de la 1 a la x:");
            planaux.verTareas();

            System.out.println("Desea remover(1) o agregar(2) tareas?");
            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion == 1) {
                System.out.println("Que numero de tarea desea borrar?");
                opcion = scan.nextInt();
                scan.nextLine();
                if (opcion <= planaux.getTareas().size() && opcion > 0) {
                    System.out.println(planaux.getTareas().get(opcion - 1).getAccion() + "  ---> seguro que desea" +
                            "borrar esta tarea? s/n");
                    seguir = scan.next().charAt(0);
                    scan.nextLine();
                    if (seguir == 's' || seguir == 'S') {
                        planaux.getTareas().remove(opcion - 1);
                        System.out.println("Tarea removida.");
                    }
                } else
                    System.out.println("opcion inexistente");
            } else if (opcion == 2) {
                planaux.agregarTareasPROADM();
            } else
                System.out.println("Numero invalido.");

            System.out.println("Desea seguir modificando? s/n");
            seguir = scan.next().charAt(0);
            scan.nextLine();
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

    public boolean completarAcciones() {
        char seguir = 's';
        boolean termina = false;
        int i = 0;

        while (i < tareas.size() && (seguir == 's' || seguir == 'S')) { ///protected me sugiere atributo??????
            if (!tareas.get(i).isHecho()) {
                if (tareas.get(i) instanceof RNumerica)
                    ((RNumerica) tareas.get(i)).ingresarNum();
                else if (tareas.get(i) instanceof RTexto)
                    ((RTexto) tareas.get(i)).ingresarString();
                else if (tareas.get(i) instanceof RBooleana)
                    ((RBooleana) tareas.get(i)).ingresarSN();
                else if (tareas.get(i) instanceof RMulChoice)
                    ((RMulChoice) tareas.get(i)).ingresarOpcionMultiple();

                System.out.println("desea seguir ingresando?s/n");
                seguir = scan.next().charAt(0);
                scan.nextLine();
            }
            i++;
        }
        if (i == tareas.size()) {
            System.out.println("Ya completo todas sus tareas");
            termina = true;
        }
        return termina;
    }

    public void modificarAcciones() {
        int i = 0, opcion;
        char seguir = 's';

        System.out.println("Que accion desea modificar?");
        while (seguir == 's' || seguir == 'S') {
            while (i < tareas.size()) {
                System.out.println((i + 1) + ": " + tareas.get(i).getAccion());
                i++;
            }

            System.out.println("\nIngrese el numero de la accion:");
            opcion = scan.nextInt();
            scan.nextLine();

            if (opcion - 1 < tareas.size()) {
                if (tareas.get(opcion - 1) instanceof RNumerica)
                    ((RNumerica) tareas.get(opcion - 1)).ingresarNum();
                else if (tareas.get(opcion - 1) instanceof RTexto)
                    ((RTexto) tareas.get(opcion - 1)).ingresarString();
                else if (tareas.get(opcion - 1) instanceof RBooleana)
                    ((RBooleana) tareas.get(opcion)).ingresarSN();
                else if (tareas.get(opcion - 1) instanceof RMulChoice)
                    ((RMulChoice) tareas.get(opcion - 1)).ingresarOpcionMultiple();
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

        while (i < tareas.size()) {
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

            i++;
        }
        return alerta == 1;
    }

    public void infoTareasDiaX() { ///muestra tareas hechas en x dia
        int i = 0;

        System.out.println("\n==============================================================");
        System.out.println("|| Tareas del " + hoy + " ||");
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

            i++;
        }
        System.out.println("==============================================================\n");
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

    @Override
    public PlanDeControl clone() { ///clone por si se elimina el plan
        try {
            PlanDeControl clone = (PlanDeControl) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
