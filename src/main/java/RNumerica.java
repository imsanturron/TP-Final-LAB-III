import java.util.Scanner;

public class RNumerica extends Realizar {
    private float dato = -9999;
    private Scanner scan = new Scanner(System.in);

    public RNumerica() {
        super();
    }

    public RNumerica(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarNum() {
        System.out.println("Ingrese el dato numerico (accion:" + accion + ") :");
        dato = scan.nextFloat();
        scan.nextLine();
        hecho = true;
    }

    public float getDato() {
        return dato;
    }

    public void setDato(float dato) {
        this.dato = dato;
    }
}
