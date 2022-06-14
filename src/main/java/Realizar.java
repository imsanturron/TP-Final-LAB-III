import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = RNumerica.class, name = "RNumerica"),
        @JsonSubTypes.Type(value = RBooleana.class, name = "RBooleana"),
        @JsonSubTypes.Type(value = RTexto.class, name = "RTexto"),
        @JsonSubTypes.Type(value = RMulChoice.class, name = "RMulChoice")
})
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


    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getMsjEspecifico() {
        return msjEspecifico;
    }

    public void setMsjEspecifico(String msjEspecifico) {
        this.msjEspecifico = msjEspecifico;
    }

    @Override
    public String toString() {
        return "Realizar{  " +
                "Tarea:'" + accion + '\'' +
                ",  Especificacion/es:'" + msjEspecifico + '\'' +
                '}';
    }
}
