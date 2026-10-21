package vista;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author ERICK
 */
class PanelRedondeado extends JPanel {

    private final int arco;
    private final Color relleno;
    private final Color borde;

    PanelRedondeado(int arco, Color relleno, Color borde) {
        this.arco = arco;
        this.relleno = relleno;
        this.borde = borde;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (relleno != null) {
            g2.setColor(relleno);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);
        }
        if (borde != null) {
            g2.setColor(borde);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arco, arco);
        }
        g2.dispose();
        super.paintComponent(g);
    }
}
