public class Realizar {
    protected String accion;
    protected String msjEspecifico;

    public Realizar(String accion, String msjEspecifico) {
        this.accion = accion;
        this.msjEspecifico = msjEspecifico;
    }

    @Override
    public String toString() {
        return "Realizar{" +
                "Tarea:'" + accion + '\'' +
                ", Especificacion:'" + msjEspecifico + '\'' +
                '}';
    }
}
