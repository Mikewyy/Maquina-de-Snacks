package maquina_snacks_archivos.dominio;

import java.io.Serializable;
import java.util.Objects;

//implementamos javabeans
public class Snack implements Serializable {
    private static int contadorSnacks = 0;
    private int idSnack;
    private String nombre;
    private double precio;

    public Snack(){
        //por cada objeto que se cree de tipo Snack se aumentara el id
        this.idSnack = ++Snack.contadorSnacks;
    }

    public Snack(String nombre, double precio){
        this();//Debe ser la primer linea. Manda a llamar al constructor vacio para inicializar idSnack
        this.nombre = nombre;
        this.precio = precio;
    }

    public static int getContadorSnacks() {
        return contadorSnacks;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ID: " + idSnack +
                ", Nombre: '" + nombre + '\'' +
                ", Precio: $" + precio;
    }

    public String escribirSnack(){
        return idSnack + "," + nombre + "," + precio;
    }



    //generamos metodo equals y hashCode
    @Override
    public boolean equals(Object o) {//Realiza comparaciones de contenido
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack && Double.compare(precio, snack.precio) == 0 && Objects.equals(nombre, snack.nombre);
    }

    @Override
    public int hashCode() {//Realiza comparaciones de tipo numerico
        return Objects.hash(idSnack, nombre, precio);
    }
}
