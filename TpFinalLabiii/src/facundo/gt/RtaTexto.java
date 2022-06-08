package facundo.gt;

import java.util.Scanner;

public class RtaTexto extends Tarea{
    private String dato= "";

    public RtaTexto(String accion, String msjEspecifico) {
        super(accion, msjEspecifico);
    }

    public void ingresarString() {
        Scanner scan = new Scanner(System.in);
        System.out.println("ingrese su respuesta: ");
        dato= scan.nextLine();
        setRealizado(true);
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }
}
