package org.example;

public class PresionAtmosferica extends CondicionClimatica {
    public PresionAtmosferica(String entrada){
        super(entrada,"hPa");
    }

    @Override
    public String calcularEstado() {
        if(valor>12){
            return "presion alta";
        }
        else if(valor>7){
            return "presion moderada";
        }
        else{
            return "presion baja";
        }
    }
}
