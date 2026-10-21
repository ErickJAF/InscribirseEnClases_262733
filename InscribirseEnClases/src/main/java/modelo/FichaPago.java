package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ERICK
 */
public class FichaPago {

    private Alumno alumno;
    private List<Curso> cursosInscritos;
    private Double totalPagar;

    public FichaPago(List<Curso> cursosAInscribir, Alumno alumno) {
        this.alumno = alumno;
        this.cursosInscritos = new ArrayList<>(cursosAInscribir);
        calcularTotal();
    }

    private void calcularTotal() {
        double suma = 0;
        for (Curso curso : cursosInscritos) {
            suma += curso.getCosto();
        }
        this.totalPagar = suma;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public List<Curso> getCursosInscritos() {
        return new ArrayList<>(cursosInscritos);
    }

    public Double getTotalPagar() {
        return totalPagar;
    }
}
