import java.util.HashMap;
import java.util.Map;

public class Login {
    private String usuario;
    private String contraseña;

    public Login(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public Usuario inicioDeSesion(HashMap<Integer, Usuario> usuariosDelSistema) {
        //Paciente pa = new Paciente("facu",TipoUsuario.PACIENTE,"41307695","1234","2236839973","dearrea",null,"25");
        Profesional pr = new Profesional("carlos", TipoUsuario.PROFESIONAL, "44307696", "12345", "2236839973", "26");
        Administrador ad = new Administrador("benjamin", TipoUsuario.ADMINISTRADOR, "41307697", "123456", "2236839973", "27");
        // usuariosDelSistema.put(pa.hashCode(),pa);
        usuariosDelSistema.put(pr.hashCode(), ad);
        usuariosDelSistema.put(ad.hashCode(), pr);
        //Usuario o = null;

        for (Map.Entry<Integer, Usuario> entry : usuariosDelSistema.entrySet()) {

            if (entry.getValue().getDNI().equals(usuario) && entry.getValue().getContrasena().equals(contraseña)) {

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
        return contraseña;
    }

    public String getUsuario() {
        return usuario;
    }
}
