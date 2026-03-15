package maquina_snacks_archivos.presentacion;

import maquina_snacks_archivos.dominio.Snack;
import maquina_snacks_archivos.servicio.IServicioSnacks;
import maquina_snacks_archivos.servicio.ServicioSnacksArchivos;
import maquina_snacks_archivos.servicio.ServicioSnacksLista;

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
        //creamos el objeto para obtener el servicio de snacks (lista)
        //IServicioSnacks servicioSnacks = new ServicioSnacksLista();
        IServicioSnacks servicioSnacks = new ServicioSnacksArchivos();
        //Creamos la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maquina de Snacks ***");
        servicioSnacks.mostrarSnacks(); //Mostrar inventario de snacks disponibles

        while(!salir){
            try{
                //mostramos el menu al usuario, esperamos que elija una opcion (consola)
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos, servicioSnacks);
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
                4. Inventario de Snakcs
                5. Salir
                Elige una opcion:\s""");
        //leemos y retornamos la opcion seleccionada por el usuario
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(consola, productos, servicioSnacks);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnack(consola, servicioSnacks);
            case 4 -> listarInventarioSnacks(consola,servicioSnacks);
            case 5 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion invalida: " + opcion);
        }
        return salir;
    }

    private static void listarInventarioSnacks(Scanner consola, IServicioSnacks servicioSnacks){
        servicioSnacks.mostrarSnacks();
    }

    private static void comprarSnack(Scanner consola, List<Snack> productos, IServicioSnacks servicioSnacks){
        System.out.print("Que snack quieres comprar (id)?: ");
        var idSnack = Integer.parseInt(consola.nextLine());
        //validamos que el snack existe
        var snackEncontrado = false;

        //verificamos que el id ingresado este en el inventario
        for (var snack: servicioSnacks.getSnacks()){
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
            ticket += "\n\t* " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\tTotal: $" + total;
        System.out.println(ticket);
    }


    private static void agregarSnack(Scanner consola, IServicioSnacks servicioSnacks){
        System.out.print("Ingrese nombre del snack: ");
        var nombreSnack = consola.nextLine();
        System.out.print("Ingrese precio del snack ");
        var precioSnack = Double.parseDouble(consola.nextLine());
        //Snacks al ser un objeto estatico no tenemos la necesidad de instanciarlo (crearlo),
        //simplemente llamamos el metodo agregarSnack
        servicioSnacks.agregarSnack(new Snack(nombreSnack,precioSnack));
        System.out.println("Tu snack se ah agregado correctamente");
        //mostramos la lista de snacks
        servicioSnacks.mostrarSnacks();
    }
}
