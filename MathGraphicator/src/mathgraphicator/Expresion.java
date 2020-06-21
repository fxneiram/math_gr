package mathgraphicator;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lsmp.djep.djep.DJep;
import org.nfunk.jep.Node;
import org.nfunk.jep.ParseException;

public class Expresion extends DJep {

    private final String formula;

    public Expresion(String Formula) {
        formula = Formula;
        init();
        this.parseExpression(formula);
    }

    public double evaluar(double val) {
        double res;
        this.addVariable("x", val);
        res = this.getValue();
        return res;
    }

    public Expresion simplificar() {
        Expresion pre;
        Node tos, simpli = null;
        tos = this.parseExpression(this.toString());
        try {
            simpli = this.simplify(tos);
        } catch (ParseException ex) {
            Logger.getLogger(Expresion.class.getName()).log(Level.SEVERE, null, ex);
        }

        pre = new Expresion(this.toString(simpli));
        return pre;
    }

    private void init() {
        this.addStandardFunctions();
        this.addStandardConstants();
        this.addComplex();
        this.setAllowAssignment(true);
        this.setAllowUndeclared(true);
        this.setImplicitMul(true);
        this.addStandardDiffRules();
    }

    @Override
    public String toString() {
        return formula;
    }
}
