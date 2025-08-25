package me.gonzager.ex.operaciones.drones;

import java.util.List;
import java.util.stream.IntStream;

import me.gonzager.ex.operaciones.misiones.InnerMision;

public abstract class Dron {
    //Atributos
    private Integer autonomia;
    private Integer procesamiento;
    private InnerMision misionActual;
    private List <Sensor> sensores;
    
    public Dron(Integer autonomia, Integer procesamiento, InnerMision misionActual, List <Sensor> sensores) {
        this.autonomia = autonomia;
        this.procesamiento = procesamiento;
        this.misionActual = misionActual;
        this.sensores = sensores;
    }

    //Getters
    public Integer getAutonomia() {
        return autonomia;
    }

    public Integer getProcesamiento() {
        return procesamiento;
    }

    public InnerMision getMisionActual() {
        return misionActual;
    }

    public List <Sensor> getSensores() {
        return sensores;
    }

    public Integer capacidadOperativa() {
        return sensores.stream().mapToInt(s -> s.getCapacidad()).sum();
    }

    //Setters
    public void setMisionActual(InnerMision misionActual) {
        this.misionActual = misionActual;
    }

    public void bajarDosDeAutonomia() {
        this.autonomia = this.autonomia - 2;
    }

    public Integer eficienciaOperativa() {
        return this.autonomia * 10 + this.misionActual.extraEficienciaOperativa(this);
    }

    public Boolean esAvanzado() {
        return this.misionActual.dronEsAvanzado(this) || this.esAvanzadoEnTipo();
    }

    protected abstract Boolean esAvanzadoEnTipo();
}
