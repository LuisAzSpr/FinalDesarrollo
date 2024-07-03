package org.example;

import org.junit.jupiter.api.Test;
import org.w3c.dom.html.HTMLMenuElement;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HumedadTest {
    @Test
    void calcularHumedadConEntradaCorrecta(){
        // Arrange
        Humedad humedad = new Humedad("90 %");
        //Act
        int valor = humedad.getValor();

        //Assert
        assertThat(valor).isEqualTo(90);
    }

    @Test
    void calcularHumedadConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        Humedad humedad = new Humedad("90%");
        //Act
        int valor = humedad.getValor();

        //Assert
        assertThat(valor).isEqualTo(9);
    }

    @Test
    void calcularHumedadConEntradaIncorrectaDebeLanzarException(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{new Humedad("83 por ciento");});

    }

    @Test
    void humedadMayor40Menor70DebeDevolverHumedadModerada(){
        // Arrange
        Humedad humedad = new Humedad("45 %");

        //Act
        String estado = humedad.getEstado();

        //Assert
        assertThat(estado).isEqualTo("humedad moderada");
    }
}
