package facundo.gt;


import java.util.Objects;

public class Usuario {
    //*ID
    protected String nombreCompleto;
    protected TipoUsuario tipoUsuario;
    protected String DNI;
    protected String edad;
    protected String contraseña;
    protected String telefono;
    protected boolean altaDeUsuario = false;


    public Usuario(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contraseña,
                   String telefono, String edad,boolean altaDeUsuario) {
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
        this.DNI = DNI;
        this.contraseña = contraseña;
        this.telefono = telefono;
        this.edad = edad;
        this.altaDeUsuario = altaDeUsuario;


    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return this.DNI == usuario.getDNI() && this.contraseña == usuario.getContraseña();
    }

    @Override
    public int hashCode() {
        return Objects.hash(DNI, contraseña);
    }

}