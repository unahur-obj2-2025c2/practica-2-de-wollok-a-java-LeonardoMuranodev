package me.gonzager.ex.operaciones;

import java.util.List;
import me.gonzager.ex.operaciones.drones.Dron;

public class Escuadron {
    private List <Dron> drones;
    
    public Escuadron() {}

    public Boolean puedeOperarEnZona(Zona unaZona) {
        return this.llevaUnDronAvanzado() && this.superaElDobleDelTamanioDeLaZona(unaZona);
    }

    public Boolean llevaUnDronAvanzado() {
        return drones.stream().anyMatch(d -> d.esAvanzado());
    }

    public Boolean superaElDobleDelTamanioDeLaZona(Zona unaZona) {
        return this.capacidadOperativaEscuadron() > unaZona.getTamanio() * 2;
    }

    public Integer capacidadOperativaEscuadron() {
        return drones.stream().mapToInt(d -> d.capacidadOperativa()).sum();
    }

    public void operarEnZona(Zona unaZona) {
        if (this.puedeOperarEnZona(unaZona)) {
            this.bajarDosDeAutonomiaADrones();
            unaZona.recibirOperacion();
        }
    }

    public void bajarDosDeAutonomiaADrones() {
        drones.stream().forEach(d -> d.bajarDosDeAutonomia());
    }

    public void agregarDron(Dron unDron) {
        if (drones.size() <= CiudadFuturista.getInstance().getLimiteEscuadron()) {
            drones.add(unDron);
        } else {
            throw new IllegalArgumentException("Supera la cantidad máxima de drones");
        }
    }

    public void eliminarDron(Dron unDron) {
        drones.remove(unDron);
    }
}
