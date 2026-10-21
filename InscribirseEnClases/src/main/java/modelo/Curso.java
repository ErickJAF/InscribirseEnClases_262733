package modelo;

/**
 *
 * @author ERICK
 */
public class Curso {

    private int id;
    private String nombre;
    private Double costo;
    private String categoria;
    private int duracionSemanas;

    public Curso(int id, String nombre, Double costo, String categoria, int duracionSemanas) {
        this.id = id;
        this.nombre = nombre;
        this.costo = costo;
        this.categoria = categoria;
        this.duracionSemanas = duracionSemanas;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCosto() {
        return costo;
    }

    public String getCategoria() {
        return categoria;
    }

    public int getDuracionSemanas() {
        return duracionSemanas;
    }

    @Override
    public String toString() {
        return nombre + " ($" + costo + ")";
    }
}
