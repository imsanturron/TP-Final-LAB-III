import java.util.Scanner;

public class RNumerica extends Realizar{
    private int dato = -9999;
    private Scanner scan = new Scanner(System.in);

    public RNumerica(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarNum(){
        System.out.println("ingrese el dato: ");
        dato = scan.nextInt();
        scan.nextLine();
        scan.close();
        hecho = true;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
}
