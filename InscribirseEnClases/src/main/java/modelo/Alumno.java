package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ERICK
 */
public class Alumno {

    private String matricula;
    private List<Curso> cursosInscritos;

    public Alumno() {
        this("262733");
    }

    public Alumno(String matricula) {
        this.matricula = matricula;
        this.cursosInscritos = new ArrayList<>();
    }

    public void setCursosInscritos(List<Curso> cursosAInscribir) {
        this.cursosInscritos = new ArrayList<>(cursosAInscribir);
    }

    public String getMatricula() {
        return matricula;
    }

    public List<Curso> getCursosInscritos() {
        return new ArrayList<>(cursosInscritos);
    }
}
