package mathgraphicator;

import grafics.ManejoDeImagen;
import grafics.Tablero;
import java.awt.Color;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import socketConection.Estado;
import socketConection.SocketServidor;

public class Ventana extends javax.swing.JFrame {

    private JTextField campoFuncion;
    private JTextField campoDerivada;
    private JTextField campoX1;
    private JTextField campoX2;
    private JPanel panelG;
    private JPanel panelConex;
    private JLabel lblIP;
    private JLabel lblHostN;
    private JTextField campoPuerto;
    private JLabel lblPuerto;
    private JButton btnConectar;
    private JButton botonGraficar;
    private JButton botonAgregar;
    private JButton botonLimpiar;
    private JButton botonDerivar;
    private final Grafica graf = new Grafica("Math Board 1.0 ", "X AXIS", "Y AXIS");
    private SocketServidor ss;
    private final ManejoDeImagen mdi = new ManejoDeImagen();
    private Estado estate;
    private Tablero ca;
    //
    private JProgressBar bar;
    //
    private Thread mph;
    private Thread mm;
    private int puerto;
    //

    public Ventana() {
        initComponents();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (this != null) {
                    try {
                        ca.setImage(mdi.createImageIcon(panelG).getImage());
                        ca.paint(ca.getGraphics());
                    } catch (Exception exx) {
                    }

                }
            }
        }).start();
    }

    private void initComponents() {

        this.setDefaultCloseOperation(3);
        this.setSize(1000, 660);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(null);
        this.setTitle("MATH BOARD 1.0");

        panelG = graf.getGrafica();
        panelG.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
        panelG.setSize(800, 550);
        panelG.setBounds(20, 10, 800, 550);
        //add(panelG);

        //codigo test
        ca = new Tablero();
        ca.setBackground(Color.WHITE);
        ca.setBounds(20, 10, 800, 550);
        ca.addMouseMotionListener(ca);
        add(ca);
        //

        botonGraficar = new javax.swing.JButton("Graficar");
        botonGraficar.setBounds(840, 100, 140, 20);
        botonGraficar.addActionListener(this::botonGraficar);
        add(botonGraficar);

        botonAgregar = new javax.swing.JButton("Agregar f(x)");
        botonAgregar.setBounds(840, 120, 140, 20);
        botonAgregar.addActionListener(this::botonAgregar);
        add(botonAgregar);

        botonDerivar = new javax.swing.JButton("Derivar f(x)");
        botonDerivar.setBounds(840, 140, 140, 20);
        botonDerivar.addActionListener(this::botonDerivar);
        add(botonDerivar);

        botonLimpiar = new javax.swing.JButton("Limpiar");
        botonLimpiar.setBounds(840, 160, 140, 20);
        botonLimpiar.addActionListener(this::botonLimpiar);
        add(botonLimpiar);

        JLabel txtcd = new JLabel("                  f '(x)=");
        txtcd.setBounds(840, 200, 140, 20);
        add(txtcd);
        campoDerivada = new JTextField();
        campoDerivada.setEditable(false);
        campoDerivada.setBounds(840, 220, 140, 20);
        add(campoDerivada);

        bar = new JProgressBar();
        bar.setBounds(840, 300, 140, 20);
        bar.setValue(0);
        bar.setStringPainted(true);
        add(bar);
        
        JLabel txtcf = new JLabel("                   f(x)=");
        txtcf.setBounds(840, 10, 140, 20);
        add(txtcf);

        campoFuncion = new javax.swing.JTextField();
        campoFuncion.setBounds(840, 25, 140, 20);
        add(campoFuncion);

        JLabel txtrg = new JLabel("              [a     ,     b]");
        txtrg.setBounds(840, 50, 140, 20);
        add(txtrg);
        campoX1 = new javax.swing.JTextField();
        campoX1.setBounds(840, 70, 60, 20);
        add(campoX1);
        campoX2 = new javax.swing.JTextField();
        campoX2.setBounds(920, 70, 60, 20);
        add(campoX2);

        panelConex = new JPanel();
        panelConex.setLayout(null);
        panelConex.setBorder(javax.swing.border.LineBorder.createBlackLineBorder());
        panelConex.setBounds(20, 570, 800, 50);
        add(panelConex);

        lblIP = new JLabel();
        lblIP.setBounds(10, 10, 140, 20);
        String ip = "";
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblIP.setText("Ip : " + ip);
        panelConex.add(lblIP);

        lblHostN = new JLabel();
        lblHostN.setBounds(160, 10, 100, 20);
        String hostN = "";
        try {
            hostN = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
        lblHostN.setText("Host N : " + hostN);
        panelConex.add(lblHostN);

        lblPuerto = new JLabel("Puerto : ");
        lblPuerto.setBounds(290, 10, 60, 20);
        panelConex.add(lblPuerto);

        campoPuerto = new JTextField("1024");
        campoPuerto.setBounds(350, 10, 60, 20);
        panelConex.add(campoPuerto);

        btnConectar = new JButton("Conectar");
        btnConectar.setBounds(420, 10, 90, 20);
        btnConectar.addActionListener(this::conectarSocket);
        panelConex.add(btnConectar);

        ImageIcon ii = new ImageIcon(getClass().getResource("/data/sinc.png"));
        this.setIconImage(ii.getImage());

    }

    private void botonGraficar(java.awt.event.ActionEvent evt) {
        try {
            String def = campoFuncion.getText();
            double x0 = Double.parseDouble(campoX1.getText());
            double xn = Double.parseDouble(campoX2.getText());
            double d = 0.001;

            Funcion f = new Funcion(new Expresion(def));

            double[] x = f.rango(x0, xn, d);
            double[] y = f.evaluar(x);
            graf.crearGrafica(def, x, y);
        } catch (Exception ess) {
            JOptionPane.showMessageDialog(this, "No se encontro un intervalo"
                    + "de evaluacion [a,b], o la funcion no es valida."
                    + "", "Error de evaluacion", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void botonAgregar(java.awt.event.ActionEvent evt) {
        try {
            String def = campoFuncion.getText();
            double x0 = Double.parseDouble(campoX1.getText());
            double xn = Double.parseDouble(campoX2.getText());
            double d = 0.001;

            Funcion f = new Funcion(new Expresion(def));

            double[] x = f.rango(x0, xn, d);
            double[] y = f.evaluar(x);
            graf.agregarGrafica(def, x, y);
        } catch (Exception exc) {
            JOptionPane.showMessageDialog(this, "No se encontro un intervalo"
                    + "de evaluacion [a,b], o la funcion no es valida."
                    + "", "Error de evaluacion", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void botonDerivar(java.awt.event.ActionEvent evt) {
        try {
            String def = campoFuncion.getText();

            Funcion f = new Funcion(new Expresion(def));
            campoDerivada.setText(f.getDerivada().toString());
        } catch (Exception ppop) {
            JOptionPane.showMessageDialog(this, "No se encontro una"
                    + " funcion no es valida."
                    + "", "Error de conversion", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void conectarSocket(java.awt.event.ActionEvent evt) {
        try {
            if (mph == null) {
                puerto = Integer.parseInt(campoPuerto.getText());
                mph = new Thread(() -> {
                    ss = new SocketServidor(puerto);
                    mm = new Thread(() -> {
                        while (true) {
                            try {
                                estate = new Estado();
                                estate.setEstado(true);
                                estate.setImagen(mdi.createImageIcon(ca));
                                ss.enviar(estate);
                            } catch (Exception ec) {

                            }
                        }
                    });
                    mm.setPriority(10);
                    mm.start();
                });
                mph.setPriority(10);
                mph.start();
                btnConectar.setText("Desconectar");
            } else {
                if (!mph.isAlive()) {
                    mph.suspend();
                    mph.stop();
                    mm.suspend();
                    mm.stop();
                    ss.cerrarConeccion();
                    ss = null;
                    btnConectar.setText("Conectar");
                    mph = null;
                } else {
                    mph.suspend();
                    mph.stop();
                    mm.suspend();
                    mm.stop();
                    ss.cerrarConeccion();
                    ss = null;
                    btnConectar.setText("Conectar");
                    mph = null;
                }
            }
        } catch (Exception exe) {
            mph.suspend();
            mph.stop();
            mm.suspend();
            mm.stop();
            ss.cerrarConeccion();
            ss = null;
            btnConectar.setText("Conectar");
            mph = null;
        }

    }

    private void botonLimpiar(java.awt.event.ActionEvent evt) {
        graf.removerGraficas();
    }

    public static void main(String args[]) {

        try {
            javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {

        }
        java.awt.EventQueue.invokeLater(() -> {
            new Ventana().setVisible(true);
        });
    }

}
