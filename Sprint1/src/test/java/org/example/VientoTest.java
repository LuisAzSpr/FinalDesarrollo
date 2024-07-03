package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class VientoTest {
    @Test
    void calcularPresionConEntradaCorrecta(){
        // Arrange
        Viento viento = new Viento("50 km/h");

        //Act
        int valor = viento.getValor();

        //Assert
        assertThat(valor).isEqualTo(50);
    }

    @Test
    void calcularPresionConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        Viento viento = new Viento("50km/h");

        //Act
        int valor = viento.getValor();

        //Assert
        assertThat(valor).isEqualTo(5);
    }

    @Test
    void calcularPresionConEntradaIncorrectaDebeLanzarException(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{new Viento("50 kilometros por hora");});

    }

    @Test
    void vientoMayor50DebeDevolverVientoFuerte(){
        // Arrange
        Viento viento = new Viento("55 km/h");

        //Act
        String estado = viento.getEstado();

        //Assert
        assertThat(estado).isEqualTo("viento fuerte");
    }
}