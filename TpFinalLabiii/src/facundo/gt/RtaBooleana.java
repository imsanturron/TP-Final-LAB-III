package facundo.gt;

import java.util.Scanner;

public class RtaBooleana extends Tarea {
    private boolean dato = false;

    public RtaBooleana(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarSN() {
        Scanner scan = new Scanner(System.in);
        String ingresa;
        System.out.println("Ingrese: si/no...");
        ingresa = scan.nextLine();
        setRealizado(true);
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
