package mathgraphicator;

import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Grafica {

    private final JFreeChart grafica;
    private final XYSeriesCollection datos = new XYSeriesCollection();
    private String titulo;

    public Grafica() {
        grafica = ChartFactory.createXYLineChart(titulo, "x", "y", datos);
    }

    public Grafica(String Titulo, String X, String Y) {
        grafica = ChartFactory.createXYLineChart(Titulo, X, Y, datos);
    }

    public void agregarGrafica(String id, double[] x, double[] y) {
        try {
            XYSeries s = new XYSeries(id);
            int n = x.length;
            for (int i = 0; i < n; i++) {
                s.add(x[i], y[i]);
            }
            datos.addSeries(s);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void crearGrafica(String id, double[] x, double[] y) {
        try {
            datos.removeAllSeries();
            agregarGrafica(id, x, y);
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public void removerGraficas() {
        datos.removeAllSeries();

    }

    public JPanel getGrafica() {
        return new ChartPanel(grafica);
    }

}
