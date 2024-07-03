package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    @Test
    void calcularLluviaConEntradasCorrectas(){

        // Arrange
        String entrada = "30 mm";
        String unidades = "mm";

        //Act
        int valor = Calculator.calcularValor(entrada,unidades);

        //Assert
        assertThat(valor).isEqualTo(30);
    }

    @Test
    void calcularHumedadConEntradaSinEspacioNoRetornaUltimoDigito(){
        // Arrange
        String entrada = "90%";
        String unidades = "%";

        //Act
        int valor = Calculator.calcularValor(entrada,unidades);

        //Assert
        assertThat(valor).isEqualTo(9);
    }

}
