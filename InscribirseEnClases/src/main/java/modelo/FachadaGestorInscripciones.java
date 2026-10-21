package modelo;

import utilerias.IObservador;

/**
 *
 * @author ERICK
 */
public class FachadaGestorInscripciones implements IOperacionesGestorInscripcion {

    private GestorInscripcion gestor;

    public FachadaGestorInscripciones(Alumno alumno, Contenedor contenedor) {
        this.gestor = new GestorInscripcion(alumno, contenedor);
    }

    public void agregarObservador(IObservador observador) {
        gestor.agregarObservador(observador);
    }

    @Override
    public void seleccionarCurso(int cursoId) {
        gestor.seleccionarCurso(cursoId);
    }

    @Override
    public void cancelarSeleccionCurso(int cursoId) {
        gestor.cancelarSeleccionCurso(cursoId);
    }

    @Override
    public void finalizarInscripcion() {
        gestor.finalizarInscripcion();
    }

    @Override
    public void reiniciarInscripcion() {
        gestor.reiniciarInscripcion();
    }
}
