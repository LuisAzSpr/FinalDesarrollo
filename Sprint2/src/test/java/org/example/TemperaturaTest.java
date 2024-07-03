package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

class TemperaturaTest {
    @Test
    void calcularPresionConEntradaCorrecta(){
        // Arrange
        Temperatura Temperatura = new Temperatura("35 C");

        //Act
        int valor = Temperatura.getValor();

        //Assert
        assertThat(valor).isEqualTo(35);
    }

    @Test
    void calcularPresionConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        Temperatura temperatura = new Temperatura("35C");

        //Act
        int valor = temperatura.getValor();

        //Assert
        assertThat(valor).isEqualTo(3);
    }

    @Test
    void calcularPresionConEntradaIncorrectaDebeLanzarException(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{new Temperatura("35 grados");});

    }

    @Test
    void calcularEstadoSegunLaTemperatura(){
        //Arrange
        Temperatura temperatura = new Temperatura("15 C");

        //Act
        String estado = temperatura.getEstado();

        //Assert
        assertThat(estado).isEqualTo("temperatura promedio");
    }


}