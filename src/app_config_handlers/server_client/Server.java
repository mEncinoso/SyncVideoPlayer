package app_config_handlers.server_client;

import app_config_handlers.GraphicalComponentsAccess;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server implements Runnable {

    private Socket socket = null;

    public void start() {
        Thread serverThread = new Thread(this);
        serverThread.start();
    }

    public void sendMessage(String message) {
        if (this.socket != null) {
            ObjectOutputStream oos = null;
            try {
                oos = new ObjectOutputStream(socket.getOutputStream());
                oos.writeObject(message);
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    assert oos != null;
                    oos.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            if (this.socket.isConnected()) {
                ObjectInputStream ois = null;
                try {
                    ois = new ObjectInputStream(socket.getInputStream());
                    String message = (String) ois.readObject();
                    String action = message.split(":")[0];
                    switch (action) {
                        case "time" -> {
                            int time = Integer.parseInt(message.split(":")[1]);
                            GraphicalComponentsAccess.getVideoPlayer().setTime(time);
                        }
                        case "play" -> GraphicalComponentsAccess.getVideoPlayer().play();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    try {
                        assert ois != null;
                        ois.close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        }
    }

    public void setClientSocket(Socket socket) {
        this.socket = socket;
        ServerClientAccess.setServer(this);
    }

    public boolean isConnected() {
        return this.socket.isConnected();
    }
}
