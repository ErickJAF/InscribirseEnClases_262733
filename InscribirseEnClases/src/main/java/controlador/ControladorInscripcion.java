package controlador;

import modelo.FichaPago;
import modelo.IOperacionesGestorInscripcion;
import vista.VistaConfirmacion;
import vista.VistaFichaPago;
import vista.VistaSeleccionCursos;

/**
 *
 * @author ERICK
 */
public class ControladorInscripcion {

    private IOperacionesGestorInscripcion gestor;
    private VistaSeleccionCursos vistaSeleccionCursos;
    private VistaConfirmacion vistaConfirmacion;
    private VistaFichaPago vistaFichaPago;

    public ControladorInscripcion(IOperacionesGestorInscripcion gestor) {
        this.gestor = gestor;
    }

    public void setVistaSeleccionCursos(VistaSeleccionCursos vistaSeleccionCursos) {
        this.vistaSeleccionCursos = vistaSeleccionCursos;
        this.vistaConfirmacion = new VistaConfirmacion(this, vistaSeleccionCursos);
        this.vistaFichaPago = new VistaFichaPago(this);
    }

    public void iniciarInscripcion() {
        vistaSeleccionCursos.mostrarVista();
    }

    public void procesarSeleccionarCurso(int cursoId) {
        gestor.seleccionarCurso(cursoId);
    }

    public void procesarCancelarSeleccionCurso(int cursoId) {
        gestor.cancelarSeleccionCurso(cursoId);
    }

    public void solicitarConfirmacion() {
        vistaConfirmacion.mostrarDialogo();
    }

    public void cancelarConfirmacion() {
        vistaSeleccionCursos.mostrarVista();
    }

    public void finalizarInscripcion() {
        gestor.finalizarInscripcion();
    }

    public void procesarFichaPago(FichaPago fichaPago) {
        vistaFichaPago.mostrarFichaPago(fichaPago);
    }

    public void regresarInicio() {
        gestor.reiniciarInscripcion();
        vistaSeleccionCursos.mostrarVista();
    }
}
