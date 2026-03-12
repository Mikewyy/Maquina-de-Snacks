package maquina_snacks;

import java.util.ArrayList;
import java.util.List;

public class Snacks {
    private static final List<Snack> snacks;

    //Bloque static inicializador - Inicializa atributos estaticos
    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("MiniChips", 50));
        snacks.add(new Snack("Sandwich", 140));
    }

    //agregamos un snack
    public static void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public static void mostrarSnacks(){
        var inventarioSnacks = "";
        //Recorremos la lista
        for(Snack snack: snacks){
            //concatenamos cada uno de los objetos de tipo snack
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println("--- Snacks en el inventario ---");
        System.out.println(inventarioSnacks);
    }

    //retornamos la lista de snacks
    public static List<Snack> getSnacks(){
        return snacks;
    }
}
