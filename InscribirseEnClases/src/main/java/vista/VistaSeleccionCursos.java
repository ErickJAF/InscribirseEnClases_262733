package vista;

import controlador.ControladorInscripcion;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import modelo.Curso;
import modelo.IConsultaContenedor;
import utilerias.IObservador;

/**
 *
 * @author ERICK
 */
public class VistaSeleccionCursos extends JFrame implements IObservador {

    private ControladorInscripcion controlador;
    private IConsultaContenedor consulta;

    private BotonRedondeado btnFinalizar;
    private JPanel uiListaDisponibles;
    private JPanel uiListaInscritos;
    private JLabel lblTotal;
    private JLabel lblContadorDisponibles;

    public VistaSeleccionCursos(ControladorInscripcion controlador, IConsultaContenedor consulta) {
        super("Inscribirse en Clases");
        this.controlador = controlador;
        this.consulta = consulta;
        construirInterfaz();
    }

    private void construirInterfaz() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(980, 640);
        setMinimumSize(new Dimension(820, 540));

        JPanel raiz = new JPanel(new BorderLayout());
        raiz.setBackground(Tema.FONDO);
        raiz.setBorder(new EmptyBorder(24, 24, 24, 24));

        PanelRedondeado tarjeta = new PanelRedondeado(22, Tema.TARJETA_EXTERNA, Tema.BORDE);
        tarjeta.setLayout(new BorderLayout(0, 18));
        tarjeta.setBorder(new EmptyBorder(24, 26, 26, 26));

        JLabel titulo = new JLabel("Seleccion de cursos");
        titulo.setFont(Tema.fuente(Font.BOLD, 24f));
        titulo.setForeground(Tema.TEXTO);
        tarjeta.add(titulo, BorderLayout.NORTH);

        JPanel columnas = new JPanel(new GridBagLayout());
        columnas.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.gridy = 0;

        JPanel colDisponibles = construirColumnaDisponibles();
        colDisponibles.setPreferredSize(new Dimension(100, 100));
        JPanel colInscritos = construirColumnaInscritos();
        colInscritos.setPreferredSize(new Dimension(100, 100));

        gbc.gridx = 0;
        gbc.weightx = 0.56;
        gbc.insets = new Insets(0, 0, 0, 12);
        columnas.add(colDisponibles, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.44;
        gbc.insets = new Insets(0, 12, 0, 0);
        columnas.add(colInscritos, gbc);

        tarjeta.add(columnas, BorderLayout.CENTER);
        raiz.add(tarjeta, BorderLayout.CENTER);
        setContentPane(raiz);
    }

    private JPanel construirColumnaDisponibles() {
        JPanel columna = new JPanel(new BorderLayout(0, 12));
        columna.setOpaque(false);

        JPanel cabecera = new JPanel(new BorderLayout());
        cabecera.setOpaque(false);
        JLabel titulo = crearEtiqueta("Cursos disponibles", Font.BOLD, 14f, Tema.TEXTO);
        lblContadorDisponibles = crearEtiqueta("", Font.PLAIN, 12f, Tema.TEXTO_SUAVE);
        cabecera.add(titulo, BorderLayout.WEST);
        cabecera.add(lblContadorDisponibles, BorderLayout.EAST);

        uiListaDisponibles = new JPanel();
        uiListaDisponibles.setOpaque(false);

        columna.add(cabecera, BorderLayout.NORTH);
        columna.add(envolverEnScroll(uiListaDisponibles), BorderLayout.CENTER);
        return columna;
    }

    private JPanel construirColumnaInscritos() {
        PanelRedondeado columna = new PanelRedondeado(16, Tema.TARJETA, Tema.BORDE);
        columna.setLayout(new BorderLayout(0, 12));
        columna.setBorder(new EmptyBorder(18, 18, 18, 18));

        JLabel titulo = crearEtiqueta("Cursos a inscribirse", Font.BOLD, 14f, Tema.TEXTO);

        uiListaInscritos = new JPanel();
        uiListaInscritos.setOpaque(false);

        JPanel pie = new JPanel();
        pie.setOpaque(false);
        pie.setLayout(new BoxLayout(pie, BoxLayout.Y_AXIS));

        JPanel linea = new JPanel();
        linea.setBackground(Tema.BORDE);
        linea.setPreferredSize(new Dimension(1, 1));
        linea.setMaximumSize(new Dimension(Integer.MAX_VALUE, 1));
        linea.setAlignmentX(LEFT_ALIGNMENT);

        JPanel filaTotal = new JPanel(new BorderLayout());
        filaTotal.setOpaque(false);
        filaTotal.setBorder(new EmptyBorder(14, 0, 14, 0));
        filaTotal.add(crearEtiqueta("Total", Font.PLAIN, 14f, Tema.TEXTO_SUAVE), BorderLayout.WEST);
        lblTotal = crearEtiqueta("$0", Font.BOLD, 24f, Tema.TEXTO);
        filaTotal.add(lblTotal, BorderLayout.EAST);
        filaTotal.setAlignmentX(LEFT_ALIGNMENT);
        filaTotal.setMaximumSize(new Dimension(Integer.MAX_VALUE, filaTotal.getPreferredSize().height));

        btnFinalizar = new BotonRedondeado("Finalizar inscripción", Tema.ACENTO, Tema.ACENTO_TEXTO, null,
                13f, 12, 16, 10);
        btnFinalizar.addActionListener(e -> finalizarInscripcion());
        JPanel contenedorBoton = new JPanel(new BorderLayout());
        contenedorBoton.setOpaque(false);
        contenedorBoton.add(btnFinalizar, BorderLayout.CENTER);
        contenedorBoton.setAlignmentX(LEFT_ALIGNMENT);
        contenedorBoton.setMaximumSize(new Dimension(Integer.MAX_VALUE, contenedorBoton.getPreferredSize().height));

        JLabel leyenda = crearEtiqueta("Se genera una ficha de pago", Font.PLAIN, 11f, Tema.TEXTO_SUAVE);
        leyenda.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel contenedorLeyenda = new JPanel(new BorderLayout());
        contenedorLeyenda.setOpaque(false);
        contenedorLeyenda.setBorder(new EmptyBorder(8, 0, 0, 0));
        contenedorLeyenda.add(leyenda, BorderLayout.CENTER);
        contenedorLeyenda.setAlignmentX(LEFT_ALIGNMENT);
        contenedorLeyenda.setMaximumSize(new Dimension(Integer.MAX_VALUE, contenedorLeyenda.getPreferredSize().height));

        pie.add(linea);
        pie.add(filaTotal);
        pie.add(contenedorBoton);
        pie.add(contenedorLeyenda);

        columna.add(titulo, BorderLayout.NORTH);
        columna.add(envolverEnScroll(uiListaInscritos), BorderLayout.CENTER);
        columna.add(pie, BorderLayout.SOUTH);
        return columna;
    }

    private JScrollPane envolverEnScroll(JPanel contenido) {
        JScrollPane scroll = new JScrollPane(contenido,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBorder(null);
        scroll.setOpaque(false);
        scroll.getViewport().setOpaque(false);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    private JLabel crearEtiqueta(String texto, int estilo, float tamano, java.awt.Color color) {
        JLabel etiqueta = new JLabel(texto);
        etiqueta.setFont(Tema.fuente(estilo, tamano));
        etiqueta.setForeground(color);
        return etiqueta;
    }

    private JPanel crearItemDisponible(Curso curso) {
        PanelRedondeado item = new PanelRedondeado(14, Tema.ITEM, Tema.BORDE);
        item.setLayout(new BorderLayout(12, 0));
        item.setBorder(new EmptyBorder(14, 16, 14, 16));

        JPanel datos = new JPanel(new GridLayout(2, 1, 0, 4));
        datos.setOpaque(false);
        datos.add(crearEtiqueta(curso.getNombre(), Font.BOLD, 14f, Tema.TEXTO));
        datos.add(crearEtiqueta(curso.getCategoria() + " · " + curso.getDuracionSemanas() + " semanas",
                Font.PLAIN, 12f, Tema.TEXTO_SUAVE));

        JPanel accion = new JPanel(new BorderLayout(0, 8));
        accion.setOpaque(false);
        JLabel precio = crearEtiqueta(Tema.dinero(curso.getCosto()), Font.BOLD, 15f, Tema.TEXTO);
        precio.setHorizontalAlignment(SwingConstants.RIGHT);
        BotonRedondeado btnInscribir = new BotonRedondeado("Inscribir +", Tema.INSCRIBIR_FONDO,
                Tema.INSCRIBIR_TEXTO, null, 12f, 10, 14, 5);
        btnInscribir.addActionListener(e -> seleccionarCurso(curso.getId()));
        accion.add(precio, BorderLayout.NORTH);
        accion.add(btnInscribir, BorderLayout.SOUTH);

        item.add(datos, BorderLayout.CENTER);
        item.add(accion, BorderLayout.EAST);
        return item;
    }

    private JPanel crearItemInscrito(Curso curso) {
        PanelRedondeado item = new PanelRedondeado(12, Tema.ITEM, Tema.BORDE);
        item.setLayout(new BorderLayout(10, 0));
        item.setBorder(new EmptyBorder(12, 14, 12, 14));

        JPanel datos = new JPanel(new GridLayout(2, 1, 0, 3));
        datos.setOpaque(false);
        datos.add(crearEtiqueta(curso.getNombre(), Font.BOLD, 14f, Tema.TEXTO));
        datos.add(crearEtiqueta(Tema.dinero(curso.getCosto()), Font.PLAIN, 13f, Tema.TEXTO_SUAVE));

        BotonRedondeado btnCancelar = new BotonRedondeado("Cancelar -", Tema.CANCELAR_FONDO,
                Tema.CANCELAR_TEXTO, Tema.CANCELAR_BORDE, 12f, 10, 12, 5);
        btnCancelar.addActionListener(e -> cancelarSeleccionCurso(curso.getId()));
        JPanel accion = new JPanel(new GridBagLayout());
        accion.setOpaque(false);
        accion.add(btnCancelar);

        item.add(datos, BorderLayout.CENTER);
        item.add(accion, BorderLayout.EAST);
        return item;
    }

    private JPanel crearEstadoVacio(boolean conIcono, String titulo, String detalleHtml) {
        JPanel estado = new JPanel();
        estado.setOpaque(false);
        estado.setLayout(new BoxLayout(estado, BoxLayout.Y_AXIS));

        if (conIcono) {
            PanelRedondeado icono = new PanelRedondeado(14, Tema.ITEM, Tema.BORDE);
            icono.setLayout(new GridBagLayout());
            icono.add(crearEtiqueta("+", Font.PLAIN, 20f, Tema.TEXTO_SUAVE));
            Dimension tam = new Dimension(44, 44);
            icono.setPreferredSize(tam);
            icono.setMaximumSize(tam);
            icono.setAlignmentX(CENTER_ALIGNMENT);
            estado.add(icono);
            estado.add(Box.createVerticalStrut(14));
        }

        JLabel lblTitulo = crearEtiqueta(titulo, Font.BOLD, 15f, Tema.TEXTO);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        estado.add(lblTitulo);
        estado.add(Box.createVerticalStrut(6));

        JLabel lblDetalle = crearEtiqueta(detalleHtml, Font.PLAIN, 12f, Tema.TEXTO_SUAVE);
        lblDetalle.setHorizontalAlignment(SwingConstants.CENTER);
        lblDetalle.setAlignmentX(CENTER_ALIGNMENT);
        estado.add(lblDetalle);
        return estado;
    }

    private void llenarLista(JPanel lista, List<Curso> cursos, boolean disponibles) {
        lista.removeAll();
        if (cursos.isEmpty()) {
            lista.setLayout(new GridBagLayout());
            if (disponibles) {
                lista.add(crearEstadoVacio(false, "No hay mas cursos",
                        "<html><center>Se han seleccionado todos los<br>cursos disponibles</center></html>"));
            } else {
                lista.add(crearEstadoVacio(true, "Aún no tienes cursos",
                        "<html><center>Elige un curso disponible para<br>empezar tu inscripción.</center></html>"));
            }
        } else {
            lista.setLayout(new BoxLayout(lista, BoxLayout.Y_AXIS));
            for (Curso curso : cursos) {
                JPanel item = disponibles ? crearItemDisponible(curso) : crearItemInscrito(curso);
                item.setAlignmentX(LEFT_ALIGNMENT);
                item.setMaximumSize(new Dimension(Integer.MAX_VALUE, item.getPreferredSize().height));
                lista.add(item);
                lista.add(Box.createVerticalStrut(10));
            }
        }
        lista.revalidate();
        lista.repaint();
    }

    public void mostrarVista() {
        actualizar();
        if (!isVisible()) {
            setLocationRelativeTo(null);
        }
        setVisible(true);
    }

    public void seleccionarCurso(int cursoId) {
        controlador.procesarSeleccionarCurso(cursoId);
    }

    public void cancelarSeleccionCurso(int cursoId) {
        controlador.procesarCancelarSeleccionCurso(cursoId);
    }

    public void finalizarInscripcion() {
        controlador.solicitarConfirmacion();
    }

    public void actualizarCursosDisponibles(List<Curso> cursosDisponibles) {
        llenarLista(uiListaDisponibles, cursosDisponibles, true);
        int n = cursosDisponibles.size();
        lblContadorDisponibles.setText(n + (n == 1 ? " curso" : " cursos"));
    }

    public void actualizarCursosAInscribir(List<Curso> cursosAInscribir) {
        llenarLista(uiListaInscritos, cursosAInscribir, false);
        btnFinalizar.setEnabled(!cursosAInscribir.isEmpty());
    }

    public void actualizarTotal(Double total) {
        lblTotal.setText(Tema.dinero(total));
    }

    @Override
    public void actualizar() {
        if (Boolean.TRUE.equals(consulta.getEstadoActualizarFichaPago())) {
            setVisible(false);
            controlador.procesarFichaPago(consulta.getFichaPago());
        } else {
            actualizarCursosDisponibles(consulta.getCursosDisponibles());
            actualizarCursosAInscribir(consulta.getCursosAInscribir());
            actualizarTotal(consulta.getTotal());
        }
    }
}
