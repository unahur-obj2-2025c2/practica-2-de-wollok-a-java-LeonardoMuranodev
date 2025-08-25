package me.gonzager.ex.operaciones;

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
        if (this.tieneMejorasTecnologicas) {
            return this.capacidad * 2;
        } else {
            return this.capacidad;
        }
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
