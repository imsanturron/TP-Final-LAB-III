package facundo.gt;

import java.util.Scanner;

public class RtaNumerica extends Tarea {
    private int dato = -9999;
    private Scanner scan = new Scanner(System.in);

    public RtaNumerica(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarNum(){
        System.out.println("ingrese el dato: ");
        dato = scan.nextInt();
        scan.nextLine();
        scan.close();
        setRealizado(true);
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }
}
