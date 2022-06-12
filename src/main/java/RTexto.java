import java.util.Scanner;

public class RTexto extends Realizar {
    private String dato= "";

    public RTexto(){
        super();
    }

    public RTexto(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Ingrese su respuesta: ");
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
