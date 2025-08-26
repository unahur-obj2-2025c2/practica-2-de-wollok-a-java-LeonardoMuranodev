package me.gonzager.ex.operaciones.drones;

public class Sensor {
    private final Integer capacidad;
    private final Integer durabilidad;
    private final Boolean tieneMejorasTecnologicas;
    
    public Sensor(Integer capacidad, Integer durabilidad, Boolean tieneMejorasTecnologicas) {
        this.capacidad = capacidad;
        this.durabilidad = durabilidad;
        this.tieneMejorasTecnologicas = tieneMejorasTecnologicas;
    }

    public Integer eficiencia() {
        return !this.getTieneMejorasTecnologicas() ? capacidad : 2 * capacidad;
    }

    public Boolean esDuradero() {
        return this.durabilidad > this.capacidad * 2;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public Integer getDurabilidad() {
        return durabilidad;
    }

    public Boolean getTieneMejorasTecnologicas() {
        return tieneMejorasTecnologicas;
    }

    
}
