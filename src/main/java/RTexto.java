import java.util.Scanner;

public class RTexto extends Realizar {
    private String dato= "";

    public RTexto(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese su respuesta: ");
        dato= scan.nextLine();
        hecho = true;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
