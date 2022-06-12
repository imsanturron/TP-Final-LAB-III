public class Realizar {
    protected String accion;
    protected String msjEspecifico;
    protected boolean hecho = false;

    public Realizar() {
    }

    public Realizar(String accion, String msjEspecifico) {
        this.accion = accion;
        this.msjEspecifico = msjEspecifico;
    }

    public boolean isHecho() {
        return hecho;
    }

    public String getAccion() {
        return accion;
    }

    public void setHecho(boolean hecho) {
        this.hecho = hecho;
    }

    @Override
    public String toString() {
        return "Realizar{" +
                "Tarea:'" + accion + '\'' +
                ", Especificacion:'" + msjEspecifico + '\'' +
                '}';
    }
}
