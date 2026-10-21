package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author ERICK
 */
public class Contenedor implements IConsultaContenedor {

    private final List<Curso> catalogoInicial;
    private List<Curso> cursosDisponibles;
    private List<Curso> cursosAInscribir;
    private Double total;
    private Boolean estadoActualizarFichaPago;
    private FichaPago fichaPago;

    public Contenedor(List<Curso> cursos) {
        this.catalogoInicial = new ArrayList<>(cursos);
        this.cursosDisponibles = new ArrayList<>(cursos);
        ordenarDisponibles();
        this.cursosAInscribir = new ArrayList<>();
        this.total = 0.0;
        this.estadoActualizarFichaPago = false;
        this.fichaPago = null;
    }

    public void agregarCursoDisponible(Curso curso) {
        cursosDisponibles.add(curso);
        ordenarDisponibles();
    }

    public void agregarCursoAInscribir(Curso curso) {
        cursosAInscribir.add(curso);
        calcularTotal();
    }

    public void eliminarCursoDisponible(Curso curso) {
        cursosDisponibles.remove(curso);
    }

    public void eliminarCursoAInscribir(Curso curso) {
        cursosAInscribir.remove(curso);
        calcularTotal();
    }

    public void eliminarCursosAInscribir() {
        cursosAInscribir.clear();
        calcularTotal();
    }

    public Boolean hayCursosAInscribir() {
        return !cursosAInscribir.isEmpty();
    }

    public void reiniciar() {
        this.cursosDisponibles = new ArrayList<>(catalogoInicial);
        ordenarDisponibles();
        this.cursosAInscribir.clear();
        this.fichaPago = null;
        this.estadoActualizarFichaPago = false;
        calcularTotal();
    }

    public void calcularTotal() {
        double suma = 0;
        for (Curso curso : cursosAInscribir) {
            suma += curso.getCosto();
        }
        this.total = suma;
    }

    private void ordenarDisponibles() {
        cursosDisponibles.sort(Comparator.comparingInt(Curso::getId));
    }

    @Override
    public List<Curso> getCursosDisponibles() {
        return Collections.unmodifiableList(cursosDisponibles);
    }

    @Override
    public List<Curso> getCursosAInscribir() {
        return Collections.unmodifiableList(cursosAInscribir);
    }

    @Override
    public Double getTotal() {
        return total;
    }

    @Override
    public Boolean getEstadoActualizarFichaPago() {
        return estadoActualizarFichaPago;
    }

    public void setEstadoActualizarFichaPago(Boolean estado) {
        this.estadoActualizarFichaPago = estado;
    }

    @Override
    public FichaPago getFichaPago() {
        return fichaPago;
    }

    public void setFichaPago(FichaPago fichaPago) {
        this.fichaPago = fichaPago;
    }
}
