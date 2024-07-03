package org.example;

public class Humedad extends CondicionClimatica {

    public Humedad(String entrada){
        super(entrada,"%");
    }

    @Override
    public String calcularEstado() {
        if(valor>70){
            return "humedad alta";
        }
        else if(valor>40){
            return "humedad moderada";
        }
        else{
            return "humedad baja";
        }
    }
}


