public class Usuario {
//ID
    protected String nombreCompleto;
    protected TipoUsuario tipoUsuario;
    protected String DNI;
    protected String edad;
    protected String contrasena;
    protected String telefono;
    protected boolean altaDeUsuario = false; ///para que quede guardado

    public Usuario(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                   String telefono, String edad) {
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
        this.DNI = DNI;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.edad = edad;
        altaDeUsuario = true;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getDNI() {
        return DNI;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isAltaDeUsuario() {
        return altaDeUsuario;
    }

    public void setAltaDeUsuario(boolean altaDeUsuario) {
        this.altaDeUsuario = altaDeUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }


}
