package vista;

import controlador.ControladorInscripcion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import modelo.Curso;
import modelo.FichaPago;

/**
 *
 * @author ERICK
 */
public class VistaFichaPago extends JFrame {

    private ControladorInscripcion controlador;
    private BotonRedondeado btnRegresar;

    private JPanel panelFilas;
    private JLabel lblTotalPagar;

    public VistaFichaPago(ControladorInscripcion controlador) {
        super("Ficha de pago");
        this.controlador = controlador;
        construirInterfaz();
    }

    private void construirInterfaz() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(980, 640);
        setMinimumSize(new Dimension(820, 540));

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(Tema.FONDO);
        raiz.setBorder(new EmptyBorder(24, 24, 24, 24));

        PanelRedondeado marco = new PanelRedondeado(22, Tema.TARJETA_EXTERNA, Tema.BORDE);
        marco.setLayout(new BorderLayout(0, 18));
        marco.setBorder(new EmptyBorder(24, 26, 26, 26));

        JLabel titulo = new JLabel("Tu ficha de pago");
        titulo.setFont(Tema.fuente(Font.BOLD, 24f));
        titulo.setForeground(Tema.TEXTO);
        marco.add(titulo, BorderLayout.NORTH);

        PanelRedondeado tarjeta = new PanelRedondeado(16, Tema.TARJETA, Tema.BORDE);
        tarjeta.setLayout(new BorderLayout(0, 10));
        tarjeta.setBorder(new EmptyBorder(20, 22, 20, 22));

        JLabel etiquetaFicha = new JLabel("FICHA DE PAGO");
        etiquetaFicha.setFont(Tema.conEspaciado(Tema.fuente(Font.BOLD, 11f), 0.12f));
        etiquetaFicha.setForeground(Tema.TEXTO_SUAVE);

        JPanel encabezados = new JPanel(new BorderLayout());
        encabezados.setOpaque(false);
        encabezados.add(crearEncabezado("CURSO"), BorderLayout.WEST);
        encabezados.add(crearEncabezado("IMPORTE PESOS"), BorderLayout.EAST);

        JPanel cabecera = new JPanel();
        cabecera.setOpaque(false);
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
        etiquetaFicha.setAlignmentX(LEFT_ALIGNMENT);
        encabezados.setAlignmentX(LEFT_ALIGNMENT);
        encabezados.setBorder(new EmptyBorder(14, 0, 8, 0));
        cabecera.add(etiquetaFicha);
        cabecera.add(encabezados);
        cabecera.add(crearLinea());

        panelFilas = new JPanel();
        panelFilas.setOpaque(false);
        panelFilas.setLayout(new BoxLayout(panelFilas, BoxLayout.Y_AXIS));

        JPanel filaTotal = new JPanel(new BorderLayout());
        filaTotal.setOpaque(false);
        filaTotal.setBorder(new EmptyBorder(14, 0, 0, 0));
        JLabel etiquetaTotal = new JLabel("Total a pagar");
        etiquetaTotal.setFont(Tema.fuente(Font.BOLD, 15f));
        etiquetaTotal.setForeground(Tema.TEXTO);
        lblTotalPagar = new JLabel("$0");
        lblTotalPagar.setFont(Tema.fuente(Font.BOLD, 24f));
        lblTotalPagar.setForeground(Tema.TEXTO);
        filaTotal.add(etiquetaTotal, BorderLayout.WEST);
        filaTotal.add(lblTotalPagar, BorderLayout.EAST);

        JPanel pieTarjeta = new JPanel(new BorderLayout());
        pieTarjeta.setOpaque(false);
        pieTarjeta.add(crearLinea(), BorderLayout.NORTH);
        pieTarjeta.add(filaTotal, BorderLayout.CENTER);

        tarjeta.add(cabecera, BorderLayout.NORTH);
        tarjeta.add(panelFilas, BorderLayout.CENTER);
        tarjeta.add(pieTarjeta, BorderLayout.SOUTH);

        btnRegresar = new BotonRedondeado("Regresar a la seleccion de cursos", Tema.ACENTO,
                Tema.ACENTO_TEXTO, null, 13f, 12, 16, 12);
        btnRegresar.addActionListener(e -> regresarAInicio());

        JPanel zonaBoton = new JPanel(new BorderLayout());
        zonaBoton.setOpaque(false);
        zonaBoton.add(btnRegresar, BorderLayout.NORTH);

        JPanel contenido = new JPanel(new BorderLayout(0, 16));
        contenido.setOpaque(false);
        contenido.add(tarjeta, BorderLayout.NORTH);
        contenido.add(zonaBoton, BorderLayout.CENTER);

        marco.add(contenido, BorderLayout.CENTER);
        raiz.add(marco, BorderLayout.CENTER);
        setContentPane(raiz);
    }

    private JLabel crearEncabezado(String texto) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(Tema.conEspaciado(Tema.fuente(Font.BOLD, 11f), 0.1f));
        etiqueta.setForeground(Tema.TEXTO_SUAVE);
        return etiqueta;
    }

    private JPanel crearLinea() {
        JPanel linea = new JPanel();
        linea.setBackground(Tema.BORDE);
        linea.setPreferredSize(new Dimension(1, 1));
        linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        linea.setAlignmentX(LEFT_ALIGNMENT);
        return linea;
    }

    private JPanel crearFila(Curso curso) {
        JPanel fila = new JPanel(new BorderLayout());
        fila.setOpaque(false);
        fila.setBorder(new EmptyBorder(11, 0, 11, 0));

        JLabel nombre = new JLabel(curso.getNombre());
        nombre.setFont(Tema.fuente(Font.PLAIN, 14f));
        nombre.setForeground(Tema.TEXTO);
        JLabel importe = new JLabel(Tema.dinero(curso.getCosto()));
        importe.setFont(Tema.fuente(Font.PLAIN, 14f));
        importe.setForeground(Tema.TEXTO);

        fila.add(nombre, BorderLayout.WEST);
        fila.add(importe, BorderLayout.EAST);
        fila.setAlignmentX(LEFT_ALIGNMENT);
        fila.setMaximumSize(new Dimension(Integer.MAX_VALUE, fila.getPreferredSize().height));
        return fila;
    }

    public void mostrarFichaPago(FichaPago fichaPago) {
        panelFilas.removeAll();
        List<Curso> cursos = fichaPago.getCursosInscritos();
        for (int i = 0; i < cursos.size(); i++) {
            if (i > 0) {
                panelFilas.add(crearLinea());
            }
            panelFilas.add(crearFila(cursos.get(i)));
        }
        lblTotalPagar.setText(Tema.dinero(fichaPago.getTotalPagar()));
        panelFilas.revalidate();
        panelFilas.repaint();

        if (!isVisible()) {
            setLocationRelativeTo(null);
        }
        setVisible(true);
    }

    public void regresarAInicio() {
        setVisible(false);
        controlador.regresarInicio();
    }
}
