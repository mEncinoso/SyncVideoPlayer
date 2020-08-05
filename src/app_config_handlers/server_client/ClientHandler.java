package app_config_handlers.server_client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private ServerSocket ss;
    private Server server;

    public ClientHandler() {
        try {
            this.ss = new ServerSocket(0);
            server = new Server();
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        Thread chThread = new Thread(this);
        chThread.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Socket s = ss.accept();
                server.setClientSocket(s);
                server.start();
                System.out.println("Client connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerSocket(){
        return ss;
    }
}
