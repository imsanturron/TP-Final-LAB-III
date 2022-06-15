import java.util.InputMismatchException;

/*
https://github.com/imsanturron/TP-Final-LAB-III.git
 */
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        boolean hecho = false;

        while (!hecho) {
            try {
                sistema.menu();
                hecho = true;
            } catch (InputMismatchException e) {
                System.out.println("\nDato de tipo incorrecto! Vuelve a ingresar al sistema.");
            }
        }

        /**                       ------------------MANUAL DE USO-------------------
         *
         * Datos de administrador hardcodeado:(nombre:"admin master", tipo-usuario:Administrador, DNI:9999,
         * contraseña:1234, telefono:155, edad:40
         *
         * El sistema iniciara con un administrador, que podra ingresar un profesional, una enfermedad, o crear un plan.
         * El administrador NO podra ingresar un paciente antes que un profesional, esto simplemente no tendria lugar en
         * la realidad. Cuando el administrador agregue un paciente, luego de haber realizado lo anteriormente dicho,
         * este no deberia ingresar al sistema antes que el medico le asigne un primer plan en caso de ser su primera
         * vez en el sistema, esto tampoco tendria logica.
         * La primera vez que ingrese al sistema, y hasta que no ingrese un profesional y un paciente, aparecera por
         * consola la excepcion controlada, indicando que "El archivo JSON actualmente esta vacio".
         * Cuando se crea un usuario nuevo, su contraseña por default sera su dni, debiendo este cambiarla la primera
         * vez que ingrese al sistema. Se adopto este modelo ya que es el que usa en algunas instituciones de salud. Su
         * nueva contraseña NO podra ser igual a su dni o menor a 3 caracteres.
         *
         * Recomendaciones: -Extender la consola poco menos de media pantalla para ver claramente los outputs.
         */

        /*
        Administrador admmm = new Administrador("admin master", TipoUsuario.ADMINISTRADOR, "9999", "1234", "155", "40");
            usuariosDelSistema.put("9999", admmm);
            administradores.put("9999", admmm);
            Persistencia.serializeHashMap(usuariosDelSistema, Archivos.USUARIOSALL.getPath());
            Persistencia.serializeHashMap(administradores, Archivos.ADMINISTRADORESALL.getPath());
         */
    }
}