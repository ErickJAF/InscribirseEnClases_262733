package modelo;

import java.util.List;

/**
 *
 * @author ERICK
 */
public interface IConsultaContenedor {

    List<Curso> getCursosDisponibles();

    List<Curso> getCursosAInscribir();

    Double getTotal();

    Boolean getEstadoActualizarFichaPago();

    FichaPago getFichaPago();
}
