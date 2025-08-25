package me.gonzager.ex.operaciones.drones;

import java.util.List;

import me.gonzager.ex.operaciones.Sensor;
import me.gonzager.ex.operaciones.misiones.InnerMision;

public class DronComercial extends Dron{

    public DronComercial(Integer autonomia, Integer procesamiento, InnerMision misionActual, List <Sensor> sensores) {
        super(autonomia, procesamiento, misionActual, sensores);
    }

    @Override
    public Integer eficienciaOperativa() {
        return super.eficienciaOperativa() + 15;
    }

    @Override
    public Boolean esAvanzadoEnTipo() {
        return false;
    }
}
