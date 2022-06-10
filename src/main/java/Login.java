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
        //Paciente pa = new Paciente("facu",TipoUsuario.PACIENTE,"41307695","1234","2236839973","dearrea",null,"25");
        Profesional pr = new Profesional("carlos", TipoUsuario.PROFESIONAL, "44307696", "12345", "2236839973", "26");
        Administrador ad = new Administrador("benjamin", TipoUsuario.ADMINISTRADOR, "41307697", "123456", "2236839973", "27");
        Paciente pc = new Paciente("juan", TipoUsuario.PACIENTE, "25896", "1234", "234325", "ewrw", pr, "rr");
        // usuariosDelSistema.put(pa.hashCode(),pa);
        usuariosDelSistema.put(pr.getDNI(), pr);
        usuariosDelSistema.put(ad.getDNI(), ad);
        usuariosDelSistema.put(pc.getDNI(), pc);
        //Usuario o = null;

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
