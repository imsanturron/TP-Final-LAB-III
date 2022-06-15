public enum TipoUsuario {
    ADMINISTRADOR(1), PACIENTE(2), PROFESIONAL(3);
    private int tipo;

    TipoUsuario(int tipo) {
        this.tipo = tipo;
    }

    public int getTipo() {
        return tipo;
    }
}
