import java.util.Scanner;

public class RBooleana extends Realizar {
    private boolean dato = false;

    public RBooleana() {
        super();
    }

    public RBooleana(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarSN() {
        Scanner scan = new Scanner(System.in);
        char ingresa;
        System.out.println("Ingrese su respuesta binaria: s/n... (accion:" + accion + ")");
        ingresa = scan.next().charAt(0);
        scan.nextLine();
        hecho = true;
        dato = ingresa == 's' | ingresa == 'S';
    }

    public boolean isDato() {
        return dato;
    }

    public void setDato(boolean dato) {
        this.dato = dato;
    }


}
