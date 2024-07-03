package org.example;

public class Lluvia extends CondicionClimatica{
    public Lluvia(String entrada){
        super(entrada,"mm");
    }


    @Override
    public String calcularEstado() {
        if(valor>30){
            return "lluvia intensa";
        }
        else if(valor>20){
            return "lluvia moderada";
        }
        else{
            return "poca lluvia";
        }
    }
}
