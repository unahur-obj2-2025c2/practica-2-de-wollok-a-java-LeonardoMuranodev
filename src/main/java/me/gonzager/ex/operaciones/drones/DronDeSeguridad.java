package me.gonzager.ex.operaciones.drones;

import java.util.List;

import me.gonzager.ex.operaciones.Sensor;
import me.gonzager.ex.operaciones.misiones.InnerMision;

public class DronDeSeguridad extends Dron {
    public DronDeSeguridad(Integer autonomia, Integer procesamiento, InnerMision misionActual, List <Sensor> sensores) {
        super(autonomia, procesamiento, misionActual, sensores);
    }

    @Override
    public Boolean esAvanzadoEnTipo() {
        return this.getProcesamiento() > 50;
    }
}
