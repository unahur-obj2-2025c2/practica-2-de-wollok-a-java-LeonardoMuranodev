package me.gonzager.ex.operaciones.misiones;

import me.gonzager.ex.operaciones.drones.Dron;

public class MisionTransporte implements InnerMision{
    @Override
    public Integer extraEficienciaOperativa(Dron unDron) {
        return 100;
    }

    @Override
    public Boolean dronEsAvanzado(Dron unDron) {
        return unDron.getAutonomia() > 50;
    }
}
