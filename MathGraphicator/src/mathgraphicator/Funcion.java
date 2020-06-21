package mathgraphicator;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class Funcion {

    private final Grafica grafica;
    private Expresion formula;

    public Funcion(Expresion f) {
        grafica = new Grafica();
        formula = f;
    }

    public double evaluar(double valor) {
        double res = formula.evaluar(valor);
        return res;
    }

    public double[] evaluar(double[] valor) {
        double[] res = new double[valor.length];

        for (int x = 0; x < valor.length; x++) {
            res[x] = formula.evaluar(valor[x]);
        }
        return res;
    }

    public double[] rango(double x0, double xn, double d) {
        int n = (int) Math.abs((xn - x0) / d);
        double[] r = new double[n];
        for (int i = 0; i < n; i++) {
            r[i] = x0;
            x0 += d;
        }
        return r;
    }

    public Expresion getDerivada() {
        Expresion dev = null;
        try {

            Node nn = formula.parseExpression(formula.toString());
            Node dd = formula.differentiate(nn, "x");
            Node re = formula.simplify(dd);
            dev = new Expresion(formula.toString(re));
        } catch (ParseException ex) {
            Logger.getLogger(Funcion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dev;
    }

    public void setFormula(Expresion _formula) {
        formula = _formula;
    }

    public Expresion getFormula() {
        return formula;
    }

    public Grafica getGrafica() {
        return grafica;
    }

    @Override
    public String toString() {
        return formula.toString();
    }
}
