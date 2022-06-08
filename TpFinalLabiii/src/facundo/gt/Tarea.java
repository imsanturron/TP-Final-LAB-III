package facundo.gt;
import java.util.Scanner;
public class Tarea {
    private String AccionDelDia;
    private String sinopsis;
    private String rta;
    private boolean realizado;


    public Tarea(String accion,String sinopsis){
        this.AccionDelDia = accion;
        this.sinopsis = sinopsis;
        this.realizado = false;
        this.rta = null;
    }

    public void setAccion(String accion) {
        this.AccionDelDia = accion;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public boolean isRealizado() {
        return realizado;
    }

    public String getAccionDelDia() {
        return AccionDelDia;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "AccionDelDia='" + AccionDelDia + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                ", rta='" + rta + '\'' +
                ", realizado=" + realizado +
                '}';
    }
}
