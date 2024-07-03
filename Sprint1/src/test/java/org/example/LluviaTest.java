package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class LluviaTest {
    @Test
    void calcularLluviaConEntradaCorrecta(){
        // Arrange
        Lluvia lluvia = new Lluvia("30 mm");

        //Act
        int valor = lluvia.getValor();

        //Assert
        assertThat(valor).isEqualTo(30);
    }

    @Test
    void calcularLluviaConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        Lluvia lluvia = new Lluvia("30mm");

        //Act
        int valor = lluvia.getValor();

        //Assert
        assertThat(valor).isEqualTo(3);
    }

    @Test
    void calcularHumedadConEntradaIncorrectaDebeLanzarException(){
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(()->{new Lluvia("30 mili");});

    }

    @Test
    void lluviaMayor30DebeDevolverLluviaIntensa(){
        // Arrange
        Lluvia lluvia = new Lluvia("40 mm");

        //Act
        String estado = lluvia.getEstado();

        //Assert
        assertThat(estado).isEqualTo("lluvia intensa");
    }

}
