package facundo.gt;

import java.util.Scanner;

public class RtaMultipleChoice extends Tarea {
    private String datOpcion;

    public RtaMultipleChoice(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
        datOpcion = "sin ingresar";
    }

    public void ingresarOpcionMultiple() {
        Scanner scan = new Scanner(System.in);
        int option;
        System.out.println("ingrese como lo/se siente a continuacion:" +
                "1:Optimo       2:Casi en buen estado          3:Mal            4:Pesimo");
        do {
            option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1:
                    datOpcion = "Optimo";
                    break;
                case 2:
                    datOpcion = "Casi en buen estado";
                    break;
                case 3:
                    datOpcion = "Mal";
                    break;
                case 4:
                    datOpcion = "Pesimo";
                    break;
                default:
                    System.out.println("ingreso un numero invalido! Ingrese nuevamente:");
                    break;
            }
        } while (option > 4 || option < 1);
        setRealizado(true);
    }

    public String getDatOpcion() {
        return datOpcion;
    }

    public void setDatOpcion(String datOpcion) {
        this.datOpcion = datOpcion;
    }
}
