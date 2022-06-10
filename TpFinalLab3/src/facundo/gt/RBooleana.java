package facundo.gt;

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
        dato = ingresa.equalsIgnoreCase("si");
    }

    public boolean isDato() {
        return dato;
    }

    public void setDato(boolean dato) {
        this.dato = dato;
    }
}
