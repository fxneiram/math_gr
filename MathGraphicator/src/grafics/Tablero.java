package grafics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class Tablero extends JPanel implements MouseMotionListener {

    private int x1;
    private int y1;
    private Image fondo;

    @Override
    public void paint(Graphics g) {
        g.drawImage(fondo, 0, 0, this);
        g.setColor(Color.BLACK);
        g.fillRect(x1, y1, 6, 6);
    }

    @Override
    public void mouseDragged(MouseEvent me) {

    }

    @Override
    public void mouseMoved(MouseEvent me) {
        x1 = me.getX();
        y1 = me.getY();
        paint(this.getGraphics());
    }

    public void setImage(Image imagen) {
        fondo = imagen;
    }

}
