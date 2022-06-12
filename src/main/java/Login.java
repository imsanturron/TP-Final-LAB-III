import java.util.HashMap;
import java.util.Map;

public class Login {
    private String usuario;
    private String contrasena;

    public Login(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Usuario inicioDeSesion(HashMap<String, Usuario> usuariosDelSistema) {
        for (Map.Entry<String, Usuario> entry : usuariosDelSistema.entrySet()) {

            if (entry.getValue().getDNI().equals(usuario) && entry.getValue().getContrasena().equals(contrasena)) {

                if (entry.getValue() instanceof Profesional) {
                    return entry.getValue();
                } else if (entry.getValue() instanceof Administrador) {
                    return entry.getValue();
                } else {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    public String getContraseña() {
        return contrasena;
    }

    public String getUsuario() {
        return usuario;
    }
}
