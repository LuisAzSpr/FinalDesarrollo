package org.example;


//Importante:

// Las entradas deben contener un espacio entre el valor numerico y las unidades!!!


// Apartir de la entrada del usuario y unas unidades establecidas, verifica si la entrada
//del usuario es correcta, si es correcta entonces devuelve el valor correspodiente como un entero
// si no es correcta lanza una exception.
public class Calculator {
    public static int calcularValor(String entrada,String unidades) {
        for (int i = 0; i < entrada.length(); i++) {
            if(entrada.substring(i).equals(unidades)){ // verificamos que alguna de las subcadenas se encuentre la unidad
                try{
                    int valor = Integer.parseInt(entrada.substring(0,i-1)); // convertimos a numero
                    return valor;
                }catch(Exception e){
                    lanzarException();
                }
            }
        }
        lanzarException();
        return -1;
    }

    private static void lanzarException(){
        throw new IllegalArgumentException("Las unidades son incorrectas");
    }

}
