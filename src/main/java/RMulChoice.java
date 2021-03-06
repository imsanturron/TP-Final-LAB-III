import java.util.Scanner;

public class RMulChoice extends Realizar {

    private String datOpcion;

    public RMulChoice() {
    }

    public RMulChoice(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
        datOpcion = "-sin ingresar-";
    }

    public void ingresarOpcionMultiple() {
        Scanner scan = new Scanner(System.in);
        int option;
        System.out.println("Ingrese como lo/se siente a continuacion (accion:" + accion + ") :" +
                "1:Optimo       2:Casi en buen estado          3:Mal            4:Pesimo");
        do {
            option = scan.nextInt();
            scan.nextLine();
            switch (option) {
                case 1 -> datOpcion = "Optimo";
                case 2 -> datOpcion = "Casi en buen estado";
                case 3 -> datOpcion = "Mal";
                case 4 -> datOpcion = "Pesimo";
                default -> System.out.println("Ingresaste un numero invalido! Ingrese nuevamente:");
            }
        } while (option > 4 || option < 1);
        hecho = true;
    }

    public String getDatOpcion() {
        return datOpcion;
    }

    public void setDatOpcion(String datOpcion) {
        this.datOpcion = datOpcion;
    }

}
