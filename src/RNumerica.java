import java.util.Scanner;

public class RNumerica extends Realizar{
    private int dato;
    private Scanner scan = new Scanner(System.in);

    public RNumerica(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public int ingresarNum(){
        System.out.println("ingrese el dato: ");
        dato = scan.nextInt();
        scan.nextLine();
        scan.close();
        return dato;
    }
}
