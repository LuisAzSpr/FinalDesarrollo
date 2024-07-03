package org.example;

public class Viento extends CondicionClimatica{
    public Viento(String entrada){
        super(entrada,"km/h");
    }

    @Override
    public String calcularEstado() {
        if(valor>50){
            return "viento fuerte";
        }
        else if(valor>25){
            return "viento moderado";
        }
        else{
            return "poco viento";
        }
    }
}
