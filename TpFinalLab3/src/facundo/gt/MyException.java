package facundo.gt;

public class MyException extends Exception{
    private char codigoError;

    public MyException(char codigoError){
        super();
        this.codigoError = codigoError;
    }
    public String getMessage(){
        String mensaje = "";

        if(codigoError != 's' || codigoError != 'S'){
            mensaje = "tratamiento no sera predeterminado";
        }
        return mensaje;
    }
}
