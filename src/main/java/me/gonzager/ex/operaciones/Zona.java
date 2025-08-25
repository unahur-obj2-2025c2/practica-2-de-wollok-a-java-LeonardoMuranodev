package me.gonzager.ex.operaciones;

import java.util.List;

public class Zona {
    private Double tamanio;
    private Integer cantOperaciones;

    public Zona(Double tamanio) {
        this.tamanio = tamanio;
    }

    public Double getTamanio() {
        return tamanio;
    }

    public Integer getCantOperaciones() {
        return cantOperaciones;
    }

    public void setTamanio(Double tamanio) {
        this.tamanio = tamanio;
    }
    
    public void recibirOperacion() {
        this.cantOperaciones += 1;
    }
}
