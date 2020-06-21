package socketConection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SocketServidor {

    private ServerSocket skServidor;
    private ArrayList<Socket> skCliente = new ArrayList();
    private ArrayList<OutputStream> os = new ArrayList();
    private ArrayList<ObjectOutputStream> oos = new ArrayList();
    private int clientesConectados = 0;

    public SocketServidor(int puerto) {
        try {
            skServidor = new ServerSocket(puerto);
            skServidor.setPerformancePreferences(1000, 1000, 5000);
            System.out.println("Iniciado en puerto : " + puerto);

        } catch (Exception e) {

        }
        new Thread(() -> {
            Socket skTemp;
            while (true) {
                try {
                    skTemp = skServidor.accept();
                    skCliente.add(skTemp);
                    System.out.println(clientesConectados + 1 + " Cliente Adicionado");
                    os.add(skCliente.get(clientesConectados).getOutputStream());
                    oos.add(new ObjectOutputStream(os.get(clientesConectados)));
                    clientesConectados++;
                } catch (Exception esssx) {
                }
            }
        }).start();
    }

    public void enviar(Object obj) {
        for (int x = 0; x < skCliente.size(); x++) {
            try {
                oos.get(x).reset();
                oos.get(x).writeObject(obj);
            } catch (IOException ex) {
                try {
                    oos.get(x).reset();
                } catch (IOException ex1) {

                }
            }
        }
    }

    public void cerrarConeccion() {
        try {
            skServidor.close();
            for (Socket skCliente1 : skCliente) {
                skCliente1.close();
            }

        } catch (Exception exe) {
        }
    }

}
