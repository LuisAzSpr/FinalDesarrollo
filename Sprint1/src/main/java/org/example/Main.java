package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ArrayList<CondicionClimatica> condiciones = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Humedad: ");
        String humedad = scanner.nextLine();
        condiciones.add(new Humedad(humedad));

        System.out.println("Luvia: ");
        String lluvia = scanner.nextLine();
        condiciones.add(new Lluvia(lluvia));

        System.out.println("Presion: ");
        String presion = scanner.nextLine();
        condiciones.add(new PresionAtmosferica(presion));

        System.out.println("Temperatura: ");
        String temperatura = scanner.nextLine();
        condiciones.add(new Temperatura(temperatura));

        System.out.println("Viento: ");
        String viento = scanner.nextLine();
        condiciones.add(new Viento(viento));

        Sistema sistema = new Sistema(condiciones);

        for(String mensaje:sistema.obtenerMensajes()){
            System.out.println(mensaje);
        }

    }

}