package components.preferences_dialog.preferences_options;

import app_config_handlers.AppProperties;
import app_config_handlers.server_client.ClientHandler;
import app_config_handlers.server_client.ServerClientAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ServerPreferences extends JPanel {
    private final JLabel jlbHost;
    private final JToggleButton jtbHost;
    private String ip;
    private int port;
    private ClientHandler clientHandler;
    private final AppProperties props;


    public ServerPreferences(AppProperties props) {
        this.props = props;
        this.jlbHost = new JLabel("Host:");
        this.jtbHost = new JToggleButton("HostMode Disabled");

        initPanelProperties();
        setContentComponents();
        activateListeners();
    }

    private void activateListeners() {
        this.jtbHost.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (jtbHost.isSelected()) {
                    clientHandler = new ClientHandler();
                    clientHandler.start();
                    ip = clientHandler.getServerSocket().getInetAddress().toString();
                    port = clientHandler.getServerSocket().getLocalPort();
                    jtbHost.setText("IP: " + ip + "\n\rPort:" + port);
                    props.setServer(true);
                } else {
                    jtbHost.setText("HostMode Disabled");
                    props.setServer(false);
                    try {
                        clientHandler.getServerSocket().close();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
    }

    private void initPanelProperties() {
        this.setLayout(new BorderLayout());
    }

    private void setContentComponents() {
        this.add(this.jlbHost, BorderLayout.WEST);
        this.add(this.jtbHost, BorderLayout.CENTER);
    }
}
