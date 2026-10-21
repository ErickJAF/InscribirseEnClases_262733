package ensamblador;

import controlador.ControladorInscripcion;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import modelo.Alumno;
import modelo.Contenedor;
import modelo.Curso;
import modelo.FachadaGestorInscripciones;
import vista.VistaSeleccionCursos;

/**
 *
 * @author ERICK
 */
public class Ensamblador {

    public void iniciarSistema() {
        Alumno alumno = new Alumno();
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso(1, "Diseño UX/UI", 120.0, "Diseño", 6));
        cursos.add(new Curso(2, "Desarrollo web", 180.0, "Tecnología", 8));
        cursos.add(new Curso(3, "Análisis de datos", 150.0, "Datos", 6));
        Contenedor contenedor = new Contenedor(cursos);
        FachadaGestorInscripciones fachada = new FachadaGestorInscripciones(alumno, contenedor);
        ControladorInscripcion controlador = new ControladorInscripcion(fachada);
        VistaSeleccionCursos vistaSeleccionCursos = new VistaSeleccionCursos(controlador, contenedor);

        controlador.setVistaSeleccionCursos(vistaSeleccionCursos);
        fachada.agregarObservador(vistaSeleccionCursos);

        controlador.iniciarInscripcion();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Ensamblador().iniciarSistema());
    }
}
