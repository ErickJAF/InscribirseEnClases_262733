package vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.Locale;
import java.util.Map;

/**
 *
 * @author ERICK
 */
final class Tema {

    static final Color FONDO = new Color(0x0F0F12);
    static final Color TARJETA_EXTERNA = new Color(0x16161A);
    static final Color TARJETA = new Color(0x1E1E23);
    static final Color ITEM = new Color(0x26262C);
    static final Color BORDE = new Color(0x2E2E36);
    static final Color TEXTO = new Color(0xF2F2F5);
    static final Color TEXTO_SUAVE = new Color(0x8C8C97);

    static final Color ACENTO = new Color(0xC9B6F2);
    static final Color ACENTO_TEXTO = new Color(0x1B1230);
    static final Color INSCRIBIR_FONDO = new Color(0x34285C);
    static final Color INSCRIBIR_TEXTO = new Color(0xD9CCFF);
    static final Color CANCELAR_FONDO = new Color(0x3A1D22);
    static final Color CANCELAR_TEXTO = new Color(0xFF7B7B);
    static final Color CANCELAR_BORDE = new Color(0x5A2A31);
    static final Color DESHABILITADO_FONDO = new Color(0x2E2E35);
    static final Color DESHABILITADO_TEXTO = new Color(0x70707B);

    private Tema() {
    }

    static Font fuente(int estilo, float tamano) {
        return new Font("Segoe UI", estilo, 12).deriveFont(tamano);
    }

    static Font conEspaciado(Font fuente, float espaciado) {
        return fuente.deriveFont(Map.of(TextAttribute.TRACKING, espaciado));
    }

    static String dinero(Double valor) {
        double v = valor == null ? 0 : valor;
        if (v == Math.rint(v)) {
            return String.format(Locale.US, "$%,.0f", v);
        }
        return String.format(Locale.US, "$%,.2f", v);
    }
}
