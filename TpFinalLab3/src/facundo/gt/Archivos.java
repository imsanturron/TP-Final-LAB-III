package facundo.gt;

public enum Archivos {
    PACIENTESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\pacientes.json"),
    PROFESIONALESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\profesionales.json"),
    ADMINISTRADORESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstallll\\administradores.json"),
    PLANESPREDET("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\plaNesControlPred.json"),
    ENFERMEDADESALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\enfermedades.json"),
    USUARIOSALL("D:\\Escritorio\\san\\TUP\\intelliJ\\tp post REINSTALL\\tplab3reinstalllll\\usuarios.json");


    private String path;

    Archivos(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}
