package me.gonzager.ex.operaciones;

public class CiudadFuturista {
    private Integer limiteEscuadron = 10;
    private static CiudadFuturista instance = new CiudadFuturista();

    private CiudadFuturista() {}

    public Integer getLimiteEscuadron() {
        return limiteEscuadron;
    }

    public static CiudadFuturista getInstance() {
        return instance;
    }

    public void setLimiteEscuadron(Integer limiteEscuadron) {
        this.limiteEscuadron = limiteEscuadron;
    }

    

}
