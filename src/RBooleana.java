import java.util.Scanner;

public class RBooleana extends Realizar {
    private boolean dato = false;

    public RBooleana(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarSN() {
        Scanner scan = new Scanner(System.in);
        String ingresa;
        System.out.println("Ingrese: si/no...");
        ingresa = scan.nextLine();
        hecho = true;
        if (ingresa.equalsIgnoreCase("si"))
            dato = true;
        else
            dato = false;
    }

    public boolean isDato() {
        return dato;
    }

    public void setDato(boolean dato) {
        this.dato = dato;
    }
}
