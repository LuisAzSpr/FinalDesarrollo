package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class Sistema {

    private ArrayList<CondicionClimatica> condiciones;

    public Sistema(ArrayList<CondicionClimatica> condiciones){
        this.condiciones = condiciones;
    }

    public ArrayList<String> obtenerMensajes(){
        ArrayList<String> arr = new ArrayList<>();
        for(CondicionClimatica condicion:condiciones){
            arr.add(condicion.getEstado());
        }
        return arr;
    }

    public ArrayList<CondicionClimatica> getCondiciones() {
        return condiciones;
    }
}
