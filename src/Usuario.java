public class Usuario {
//ID
    protected String nombreCompleto;
    protected TipoUsuario tipoUsuario;
    protected String DNI;
    protected String edad;
    protected String contrasena;
    protected String telefono;

    public Usuario(String nombreCompleto, TipoUsuario tipoUsuario, String DNI, String contrasena,
                   String telefono, String edad) {
        this.nombreCompleto = nombreCompleto;
        this.tipoUsuario = tipoUsuario;
        this.DNI = DNI;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.edad = edad;
    }
}
