package socketConection;

import java.io.Serializable;
import javax.swing.ImageIcon;

public class Estado implements Serializable{

    private ImageIcon imagen;
    private boolean estadoServidor;

    public Estado() {
        imagen = new ImageIcon();
        estadoServidor = true;
    }

    public void setImagen(ImageIcon Imagen) {
        imagen = Imagen;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public void setEstado(boolean Estado) {
        estadoServidor = Estado;
    }

    public boolean getEstado() {
        return this.estadoServidor;
    }
}
