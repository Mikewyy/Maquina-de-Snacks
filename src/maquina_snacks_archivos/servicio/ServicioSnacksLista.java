package maquina_snacks_archivos.servicio;

import maquina_snacks_archivos.dominio.Snack;

import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksLista implements IServicioSnacks{
    private static final List<Snack> snacks;

    //Bloque static inicializador - Inicializa atributos estaticos
    static{
        snacks = new ArrayList<>();
        snacks.add(new Snack("Papas", 70));
        snacks.add(new Snack("MiniChips", 50));
        snacks.add(new Snack("Sandwich", 140));
    }

    //agregamos un snack
    public void agregarSnack(Snack snack){
        snacks.add(snack);
    }

    public void mostrarSnacks(){
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
    public List<Snack> getSnacks(){
        return snacks;
    }
}
