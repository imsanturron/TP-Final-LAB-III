public enum Archivos {///Cambiar ruta dependiendo del directorio donde se guarde el proyecto
    PACIENTESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\pacientes.json"),
    PROFESIONALESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\profesionales.json"),
    ADMINISTRADORESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\administradores.json"),
    PLANESPREDET("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\planesControlPred.json"),
    ENFERMEDADESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\enfermedades.json"),
    USUARIOSALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\usuarios.json");


    private final String path;

    Archivos(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
