package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class PresionAtmosfericaTest {

    @Test
    void calcularPresionConEntradaCorrecta(){
        // Arrange
        PresionAtmosferica presion = new PresionAtmosferica("10 hPa");

        //Act
        int valor = presion.getValor();

        //Assert
        assertThat(valor).isEqualTo(10);
    }

    @Test
    void calcularPresionConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        PresionAtmosferica presion = new PresionAtmosferica("10hPa");
        //Act
        int valor = presion.getValor();

        //Assert
        assertThat(valor).isEqualTo(1);
    }

    @Test
    void calcularPresionConEntradaIncorrectaDebeLanzarException(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{new PresionAtmosferica("10 pas");});

    }

    @Test
    void presionMayor12DebeDevolverPresionAlta(){
        // Arrange
        PresionAtmosferica presion = new PresionAtmosferica("15 hPa");

        //Act
        String estado = presion.getEstado();

        //Assert
        assertThat(estado).isEqualTo("presion alta");
    }

}