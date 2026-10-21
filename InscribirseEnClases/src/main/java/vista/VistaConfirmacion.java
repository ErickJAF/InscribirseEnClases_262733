package vista;

import controlador.ControladorInscripcion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author ERICK
 */
public class VistaConfirmacion extends JDialog {

    private ControladorInscripcion controlador;
    private BotonRedondeado btnConfirmar;
    private BotonRedondeado btnCancelar;

    private final JFrame ventanaPadre;
    private final JPanel velo;

    public VistaConfirmacion(ControladorInscripcion controlador, JFrame ventanaPadre) {
        super(ventanaPadre, "Confirmación", true);
        this.controlador = controlador;
        this.ventanaPadre = ventanaPadre;

        this.velo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 150));
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        velo.setOpaque(false);
        velo.addMouseListener(new MouseAdapter() {
        });
        ventanaPadre.setGlassPane(velo);
        velo.setVisible(false);

        construirInterfaz();
    }

    private void construirInterfaz() {
        setUndecorated(true);
        try {
            setBackground(new Color(0, 0, 0, 0));
        } catch (UnsupportedOperationException ex) {

        }

        PanelRedondeado contenido = new PanelRedondeado(18, Tema.TARJETA, Tema.BORDE);
        contenido.setLayout(new BorderLayout(0, 20));
        contenido.setBorder(new EmptyBorder(26, 30, 26, 30));

        JLabel pregunta = new JLabel("¿Quieres continuar?", SwingConstants.CENTER);
        pregunta.setFont(Tema.fuente(Font.BOLD, 17f));
        pregunta.setForeground(Tema.TEXTO);

        btnConfirmar = new BotonRedondeado("Confirmar", Tema.ACENTO, Tema.ACENTO_TEXTO, null, 13f, 10, 22, 9);
        btnConfirmar.addActionListener(e -> confirmarFinalizacion());
        btnCancelar = new BotonRedondeado("Cancelar", null, Tema.TEXTO, new Color(0xE6E6EA), 13f, 10, 22, 9);
        btnCancelar.addActionListener(e -> cancelarFinalizacion());

        JPanel botones = new JPanel(new GridLayout(1, 2, 14, 0));
        botones.setOpaque(false);
        botones.add(btnConfirmar);
        botones.add(btnCancelar);

        contenido.add(pregunta, BorderLayout.NORTH);
        contenido.add(botones, BorderLayout.CENTER);
        setContentPane(contenido);
        pack();
        setMinimumSize(new Dimension(360, getHeight()));
    }

    public void mostrarDialogo() {
        velo.setVisible(true);
        pack();
        setSize(Math.max(getWidth(), 360), getHeight());
        setLocationRelativeTo(ventanaPadre);
        setVisible(true);
    }

    public void confirmarFinalizacion() {
        cerrar();
        controlador.finalizarInscripcion();
    }

    public void cancelarFinalizacion() {
        cerrar();
        controlador.cancelarConfirmacion();
    }

    private void cerrar() {
        setVisible(false);
        velo.setVisible(false);
    }
}
