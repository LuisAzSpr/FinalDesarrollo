package org.example;

import io.cucumber.java.ro.Si;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SistemaTest {

    private Sistema sistema;

    private Humedad humedadMock;
    private Lluvia lluviaMock;
    private PresionAtmosferica presionMock;
    private Temperatura temperaturaMock;
    private Viento vientoMock;

    @BeforeEach
    void setUp(){
        ArrayList<CondicionClimatica> condicionesMock = new ArrayList<>();

        // Definimos los tubs de cada Condicion climatica
        humedadMock = mock(Humedad.class);
        condicionesMock.add(humedadMock);

        lluviaMock = mock(Lluvia.class);
        condicionesMock.add(lluviaMock);

        presionMock = mock(PresionAtmosferica.class);
        condicionesMock.add(presionMock);

        temperaturaMock = mock(Temperatura.class);
        condicionesMock.add(temperaturaMock);

        vientoMock = mock(Viento.class);
        condicionesMock.add(vientoMock);

        // inyeccion de dependencias (inyectamos una dependencia por el constructor)
        sistema = new Sistema(condicionesMock);
    }

    @Test
    void mensajesTest(){
        // Apartir de llamar al estado de las condiciones climaticas, estas tienen que devolver un valor predeterminado
        when(humedadMock.getEstado()).thenReturn("humedad alta");
        when(lluviaMock.getEstado()).thenReturn("lluvia intensa");
        when(presionMock.getEstado()).thenReturn("presion alta");
        when(temperaturaMock.getEstado()).thenReturn("temperatura alta");
        when(vientoMock.getEstado()).thenReturn("viento fuerte");

        // obtenemos los mensajes del sistema que usa a los mocks
        List<String> mensajes = sistema.obtenerMensajes();

        // La idea esta en poder probar lo retorna un arreglo con los estados de las condiciones climaticas
        List<String> expected = Arrays.asList("humedad alta", "lluvia intensa", "presion alta", "temperatura alta", "viento fuerte");
        assertThat(mensajes).isEqualTo(expected);
    }
}
