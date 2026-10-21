package vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

/**
 *
 * @author ERICK
 */
class BotonRedondeado extends JButton {

    private final Color fondo;
    private final Color texto;
    private final Color borde;
    private final int arco;
    private final int margenX;
    private final int margenY;

    BotonRedondeado(String etiqueta, Color fondo, Color texto, Color borde, float tamanoFuente,
            int arco, int margenX, int margenY) {
        super(etiqueta);
        this.fondo = fondo;
        this.texto = texto;
        this.borde = borde;
        this.arco = arco;
        this.margenX = margenX;
        this.margenY = margenY;
        setFont(Tema.fuente(java.awt.Font.BOLD, tamanoFuente));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    @Override
    public Dimension getPreferredSize() {
        FontMetrics fm = getFontMetrics(getFont());
        return new Dimension(fm.stringWidth(getText()) + margenX * 2, fm.getHeight() + margenY * 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        Color colorFondo = fondo;
        Color colorTexto = texto;
        Color colorBorde = borde;
        if (!isEnabled()) {
            colorFondo = Tema.DESHABILITADO_FONDO;
            colorTexto = Tema.DESHABILITADO_TEXTO;
            colorBorde = null;
            setCursor(Cursor.getDefaultCursor());
        } else {
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            if (colorFondo != null && getModel().isRollover()) {
                colorFondo = aclarar(colorFondo, 0.12f);
            } else if (colorFondo == null && getModel().isRollover()) {
                colorFondo = new Color(255, 255, 255, 18);
            }
            if (getModel().isPressed() && colorFondo != null) {
                colorFondo = aclarar(colorFondo, -0.08f);
            }
        }

        if (colorFondo != null) {
            g2.setColor(colorFondo);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);
        }
        if (colorBorde != null) {
            g2.setColor(colorBorde);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);
        }

        g2.setFont(getFont());
        g2.setColor(colorTexto);
        FontMetrics fm = g2.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g2.drawString(getText(), x, y);
        g2.dispose();
    }

    private static Color aclarar(Color c, float factor) {
        int r = ajustar(c.getRed(), factor);
        int g = ajustar(c.getGreen(), factor);
        int b = ajustar(c.getBlue(), factor);
        return new Color(r, g, b, c.getAlpha());
    }

    private static int ajustar(int valor, float factor) {
        int nuevo = factor >= 0 ? valor + Math.round((255 - valor) * factor) : Math.round(valor * (1 + factor));
        return Math.max(0, Math.min(255, nuevo));
    }
}
