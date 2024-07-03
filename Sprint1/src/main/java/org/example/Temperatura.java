package org.example;

public class Temperatura extends CondicionClimatica{
    protected Temperatura(String entrada) {
        super(entrada,"C");
    }
    public String calcularEstado(){
        if(valor>25){
            return "temperatura alta";
        }
        else if(valor>14){
            return "temperatura promedio";
        }
        else{
            return "temperatura baja";
        }
    }

}
/*

*/
