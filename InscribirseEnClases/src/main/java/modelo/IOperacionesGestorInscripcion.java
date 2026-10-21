package modelo;

/**
 *
 * @author ERICK
 */
public interface IOperacionesGestorInscripcion {

    void seleccionarCurso(int cursoId);

    void cancelarSeleccionCurso(int cursoId);

    void finalizarInscripcion();

    void reiniciarInscripcion();
}
