package me.gonzager.ex.operaciones.misiones;

import me.gonzager.ex.operaciones.drones.Dron;

public class MisionExploracion implements InnerMision{
    @Override
    public Integer extraEficienciaOperativa(Dron unDron) {
        return 0;
    }

    @Override
    public Boolean dronEsAvanzado(Dron unDron) {
        return unDron.eficienciaOperativa() % 2 == 0;
    }

}
