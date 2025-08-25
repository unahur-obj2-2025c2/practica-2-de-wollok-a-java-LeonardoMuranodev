package me.gonzager.ex.operaciones.misiones;

import me.gonzager.ex.operaciones.drones.Dron;

public class MisionVigilancia implements InnerMision{
    @Override
    public Integer extraEficienciaOperativa(Dron unDron) {
        return unDron.getSensores().stream().mapToInt(s -> s.eficiencia()).sum();
    }

    @Override
    public Boolean dronEsAvanzado(Dron unDron) {
        return unDron.getSensores().stream().allMatch(s -> s.esDuradero());
    }
}
