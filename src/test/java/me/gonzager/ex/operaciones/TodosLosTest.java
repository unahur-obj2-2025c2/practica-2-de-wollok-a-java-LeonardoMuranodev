package me.gonzager.ex.operaciones;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import me.gonzager.ex.operaciones.drones.*;
import me.gonzager.ex.operaciones.misiones.*;

public class TodosLosTest {

    //Misiones
    private InnerMision MisionTransporte = new MisionTransporte();
    private InnerMision MisionVigilancia = new MisionVigilancia();
    private InnerMision MisionExploracion = new MisionExploracion();

    //Sensores
    private Sensor sensorDuraderoSinTecnologia = new Sensor(20, 50, false);
    private Sensor sensorDuraderoConTecnologia = new Sensor(30, 70, true);
    private Sensor sensorNoDuraderoSinTecnologia = new Sensor(40, 45, false);
    private Sensor sensorConTecnologiaNoDuradero = new Sensor(40, 45, true);

    //Drones
    private Dron dronComercial = new DronComercial(10, 10, MisionTransporte, List.of(sensorDuraderoConTecnologia, sensorDuraderoSinTecnologia));
    private Dron dronSeguridad = new DronDeSeguridad(20, 55, MisionExploracion, List.of(sensorDuraderoConTecnologia, sensorDuraderoSinTecnologia, sensorNoDuraderoSinTecnologia));
    private Dron dronComercial2 = new DronComercial(5, 10, MisionVigilancia, List.of(sensorDuraderoConTecnologia, sensorDuraderoSinTecnologia, sensorConTecnologiaNoDuradero));
    private Dron dronSeguridad2 = new DronDeSeguridad(20, 25, MisionTransporte, List.of(sensorDuraderoConTecnologia, sensorDuraderoSinTecnologia, sensorNoDuraderoSinTecnologia));
    private Dron dronComercial3 = new DronComercial(100, 5, MisionTransporte, List.of(sensorDuraderoConTecnologia, sensorDuraderoSinTecnologia, sensorNoDuraderoSinTecnologia));

    //Escuadrones
    private Escuadron escuadron = new Escuadron();

    //Zonas
    private Zona zona1 = new Zona(60.0);
    private Zona zona2 = new Zona(20.0);

    @BeforeEach
    private void init() {
        List.of(dronComercial, dronSeguridad).stream().forEach(d -> escuadron.agregarDron(d));
        zona2.setTamanio(100.0);
    }

    @Test()
    void escuadronPuedeOperarEnZonaCorrectamenteYSeBajaLaAutonomiaDelEscuadronYSeRegistraLaOperacionEnLaZona() {
        escuadron.operarEnZona(zona1);
        assertEquals(zona1.getCantOperaciones(), 1);
        assertEquals(escuadron.getDrones().get(0).getAutonomia(), 8);
        assertEquals(escuadron.getDrones().get(1).getAutonomia(), 18);
    }

    @Test()
    void escuadronNoPuedeOperarEnZonaCorrectamentePorTamanio() {
        IllegalStateException excepcion = assertThrows(IllegalStateException.class, () -> {
            escuadron.operarEnZona(zona2);
        });

        assertEquals("El escuadrón no puede operar en esta zona", excepcion.getMessage());
        assertEquals(zona1.getCantOperaciones(), 0);
        assertEquals(escuadron.getDrones().get(0).getAutonomia(), 10);
        assertEquals(escuadron.getDrones().get(1).getAutonomia(), 20);
    }

    @Test
    void seCambiaElMaximoDeEscuadronYNoSePuedeAgregarMasDrones() {
        CiudadFuturista.getInstance().setLimiteEscuadron(2);
        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            escuadron.agregarDron(dronComercial2);
        });
        assertEquals("Supera la cantidad máxima de drones", excepcion.getMessage());
    }

    @Test
    void eliminarDronSeguridadAgregarComercialDosYNoPuedeOperarEnZonaPorNoAvanzados() {
        escuadron.eliminarDron(dronSeguridad);
        escuadron.agregarDron(dronComercial2);
        IllegalStateException excepcion = assertThrows(IllegalStateException.class, () -> {
            escuadron.operarEnZona(zona2);
        });

        assertEquals("El escuadrón no puede operar en esta zona", excepcion.getMessage());
        assertEquals(zona1.getCantOperaciones(), 0);
        assertEquals(escuadron.getDrones().get(0).getAutonomia(), 10);
        assertEquals(escuadron.getDrones().get(1).getAutonomia(), 5);
    }

    @Test
    void dronSeguridasDosNoEsAvanzado() {
        assertFalse(dronSeguridad2.esAvanzado());
    }

    @Test
    void cambiarMisionActualDeDronComercialYCorroborarYNoEsAvanzadoNiPorMisionNiPorTipo() {
        dronComercial.setMisionActual(MisionExploracion);
        assertEquals(dronComercial.getMisionActual(), MisionExploracion);
        assertFalse(dronComercial.esAvanzado());
    }

    @Test
    void elSensorConTecnologiaNoDuraderoTieneDeEficienciaCapacidadPorDosYDurabilidadCuarenteYCinco() {
        assertEquals(sensorConTecnologiaNoDuradero.getDurabilidad(), 45);
        assertEquals(sensorConTecnologiaNoDuradero.eficiencia(), sensorConTecnologiaNoDuradero.getCapacidad() * 2);
    }

    @Test
    void elDronComercialTresEsAvanzadoPorSuMision() {
        assertTrue(dronComercial3.esAvanzado());
    }

    //50 + 15 + extra: 
    @Test
    void laEficienciaOpDeDronComercialTresEsDeMilQuince() {
        assertEquals(1115, dronComercial3.eficienciaOperativa());
    }

    void laEficienciaOpDeDronComercialDosEsDeAutonomiaPorDiezMasQuinceMasEficienciaDeSusSensores() {
        assertEquals(dronComercial2.eficienciaOperativa(), dronComercial2.getAutonomia() * 10 + 15 + dronComercial2.getEficienciaSensores());
    }
}
