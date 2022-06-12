import java.util.Scanner;

public class RNumerica extends Realizar{
    private float dato = -9999;
    private Scanner scan = new Scanner(System.in);

    public RNumerica(){
    super();
    }

    public RNumerica(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarNum(){
        System.out.println("Ingrese el dato: ");
        dato = scan.nextFloat();
        scan.nextLine();
        scan.close();
        hecho = true;
    }

    public float getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
}
