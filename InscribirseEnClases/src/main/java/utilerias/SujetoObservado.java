package utilerias;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ERICK
 */
public abstract class SujetoObservado {

    private final List<IObservador> observadores = new ArrayList<>();

    public void agregarObservador(IObservador observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void notificarObservadores() {

        for (IObservador observador : new ArrayList<>(observadores)) {
            observador.actualizar();
        }
    }
}
