package modelo;

import java.util.ArrayList;
import java.util.List;
import utilerias.SujetoObservado;

/**
 *
 * @author ERICK
 */
public class GestorInscripcion extends SujetoObservado {

    private Contenedor contenedor;
    private Alumno alumnoActual;

    public GestorInscripcion(Alumno alumno, Contenedor contenedor) {
        this.alumnoActual = alumno;
        this.contenedor = contenedor;
    }

    public void seleccionarCurso(int cursoId) {
        Curso curso = validarElCursoRecibido(cursoId, contenedor.getCursosDisponibles());
        iniciarCambios();
        agregarCursoAInscribir(curso);
        eliminarCursoDisponible(curso);
        notificarObservadores();
    }

    public void cancelarSeleccionCurso(int cursoId) {
        Curso curso = validarElCursoRecibido(cursoId, contenedor.getCursosAInscribir());
        iniciarCambios();
        agregarCursoDisponible(curso);
        eliminarCursoAInscribir(curso);
        notificarObservadores();
    }

    public void finalizarInscripcion() {
        if (!hayCursosAInscribir()) {
            return;
        }
        pasarCursosAInscribirAInscritos(contenedor.getCursosAInscribir(), alumnoActual);
        crearFichaPago();
        actualizarEstadoFichaPago(true);
        eliminarCursosAInscribir();
        notificarCambios();
    }

    public void reiniciarInscripcion() {
        iniciarCambios();
        alumnoActual.setCursosInscritos(new ArrayList<>());
        contenedor.reiniciar();
        notificarObservadores();
    }

    public void crearFichaPago() {
        FichaPago ficha = new FichaPago(contenedor.getCursosAInscribir(), alumnoActual);
        contenedor.setFichaPago(ficha);
    }

    private void iniciarCambios() {
        actualizarEstadoFichaPago(false);
    }

    private void agregarCursoDisponible(Curso curso) {
        contenedor.agregarCursoDisponible(curso);
    }

    private void agregarCursoAInscribir(Curso curso) {
        contenedor.agregarCursoAInscribir(curso);
    }

    private void eliminarCursoDisponible(Curso curso) {
        contenedor.eliminarCursoDisponible(curso);
    }

    private void eliminarCursoAInscribir(Curso curso) {
        contenedor.eliminarCursoAInscribir(curso);
    }

    private void notificarCambios() {
        notificarObservadores();
    }

    private Curso validarElCursoRecibido(int cursoId, List<Curso> origen) {
        for (Curso curso : origen) {
            if (curso.getId() == cursoId) {
                return curso;
            }
        }
        throw new IllegalArgumentException("El curso con id " + cursoId + " no es válido para esta operación.");
    }

    private void actualizarEstadoFichaPago(Boolean estado) {
        contenedor.setEstadoActualizarFichaPago(estado);
    }

    private void pasarCursosAInscribirAInscritos(List<Curso> cursosAInscribir, Alumno alumno) {
        alumno.setCursosInscritos(cursosAInscribir);
    }

    private void eliminarCursosAInscribir() {
        contenedor.eliminarCursosAInscribir();
    }

    private Boolean hayCursosAInscribir() {
        return contenedor.hayCursosAInscribir();
    }
}
