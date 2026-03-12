package maquina_snacks;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main(String[] args) {
        maquinaScanks();
    }

    public static void maquinaScanks(){
        var salir = false;
        var consola = new Scanner(System.in);
        //Creamos la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        Snacks.mostrarSnacks(); //Mostrar inventario de snacks disponibles

        while(!salir){
            try{
                //mostramos el menu al usuario, esperamos que elija una opcion (consola)
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos);
            }catch (Exception e){
                System.out.println("Ocurrio un error: " + e);
            }
            finally {
                System.out.println();//Imprime un salto de linea con cada iteracion
            }
        }
    }


    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar nuevo snack
                4. Salir
                Elige una opcion:\s""");
        //leemos y retornamos la opcion seleccionada por el usuario
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos);
            case 2 -> mostrarTicket(productos);
        }
        return salir;
    }


    private static void comprarSnack(Scanner consola, List<Snack> productos){
        System.out.print("Que snack quieres comprar (id)?: ");
        var idSnack = Integer.parseInt(consola.nextLine());
        //validamos que el snack existe
        var snackEncontrado = false;

        //verificamos que el id ingresado este en el inventario
        for (var snack: Snacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                //Si esta en la lista, agregamos el snack a la lista de productos
                productos.add(snack);
                System.out.println("Snack agregado: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        //de no haberlo encontrado, enviamos mensaje de error
        if(!snackEncontrado){
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de Venta ***";
        var total = 0.00;
        for (var producto : productos){
            ticket += "\n\t-" + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal: $" + total;
        System.out.println(ticket);
    }


}
