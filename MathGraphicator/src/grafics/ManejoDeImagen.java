package grafics;

import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ManejoDeImagen {

    private final Image mdesb = new ImageIcon(getClass().getResource("/data/memoria desbordad.png")).getImage();

    public BufferedImage createImage(JPanel panel) {
        BufferedImage bi;
        int w = panel.getWidth();
        int h = panel.getHeight();
        try {
            bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bi.createGraphics();
            panel.paint(g);
        } catch (Exception dd) {
            bi = (BufferedImage) mdesb;
        }
        return bi;
    }

    public static BufferedImage componentToImage(Component component) {
        BufferedImage img = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) img.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        component.paintAll(g2d);
        return img;
    }

    public ImageIcon createImageIcon(JPanel panel) {
        return new ImageIcon(createImage(panel));
    }
}
