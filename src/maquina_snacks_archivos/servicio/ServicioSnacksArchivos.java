package maquina_snacks_archivos.servicio;

import maquina_snacks_archivos.dominio.Snack;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivos implements IServicioSnacks{
    private final String NOMBRE_ARCHIVO = "snacks.txt";
    //Creamos la lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    //Constructor clase
    public ServicioSnacksArchivos(){
        //Creamos el archivo si no existe
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            existe = archivo.exists();
            if(existe){
                this.snacks = obtenerSnacks();
            }else{
                //Si no existe lo creamos
                var salida = new PrintWriter(new FileWriter(archivo));
                //Se guarda el archivo al disco
                salida.close();
                System.out.println("Se ah creado el archivo");
            }
        }catch (Exception e){
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
        //Si no existe el archivo, cargamos algunos snacks iniciales
        if(!existe)
            cargarSnacksIniciales();
    }


    private void cargarSnacksIniciales(){
        this.agregarSnack(new Snack("Papas",80));
        this.agregarSnack(new Snack("Cola",40));
        this.agregarSnack(new Snack("Sanduche",40));
    }

    private List<Snack> obtenerSnacks(){
        var snacks = new ArrayList<Snack>();
        try{
            //Leemos el archivo
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for (String linea: lineas){
                //cada vez que encuentre una coma "," separa el contenido y lo asigna al arreglo
                String[] lineaSnack = linea.split(",");//parsea separado por una coma
                var idSnack = lineaSnack[0];
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre,precio);
                snacks.add(snack);//agreamos el snack leido a la lista
            }
        } catch (Exception e) {
            System.out.println("Erro al leer archivo de snacks: " +e.getMessage());
        }
        return snacks;
    }


    @Override
    public void agregarSnack(Snack snack) {
        //Agregamos el nuevo snack,
        // //1. A la lista de memora
        this.snacks.add(snack);
        //2. Guardamos el nuevo snack en el archivo
        this.agregarSnackArchivo(snack);
    }


    private void agregarSnackArchivo(Snack snack){
        var anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);

        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo,anexar));
            salida.println(snack.escribirSnack());//imprime la informacion en el archivi
            salida.close();//Se guarda la informacion del archivo
        } catch (Exception e) {
            System.out.println("Eror al agregar contenido: " + e.getMessage());
        }
    }


    @Override
    public void mostrarSnacks() {
        System.out.println("--- Snacks en el inventario ---");
        //mostramos lista de snacks en el archivo
        var inventarioSnacks = "";
        for(var snack: this.snacks){
            inventarioSnacks += snack.toString() + "\n";
        }
        System.out.println(inventarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
