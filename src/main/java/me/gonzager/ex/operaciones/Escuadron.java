package me.gonzager.ex.operaciones;

import java.util.ArrayList;
import java.util.List;
import me.gonzager.ex.operaciones.drones.Dron;

public class Escuadron {
    private List <Dron> drones = new ArrayList<>();
    
    public Escuadron() {}

    public List<Dron> getDrones() {
        return drones;
    }

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
        } else {
            throw new IllegalStateException("El escuadrón no puede operar en esta zona");
        }
    }

    public void bajarDosDeAutonomiaADrones() {
        drones.stream().forEach(d -> d.bajarDosDeAutonomia());
    }

    public void agregarDron(Dron unDron) {
        if (drones.size() < CiudadFuturista.getInstance().getLimiteEscuadron()) {
            drones.add(unDron);
        } else {
            throw new IllegalArgumentException("Supera la cantidad máxima de drones");
        }
    }

    public void eliminarDron(Dron unDron) {
        drones.remove(unDron);
    }
}
