package components.preferences_dialog.preferences_options;

import app_config_handlers.AppProperties;
import app_config_handlers.TextPrompt;
import app_config_handlers.server_client.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClientPreferences extends JPanel {
    private final JLabel jlbGuest;
    private final JLabel jlbUnable;
    private final JPanel controlPanel;
    private final JTextField jtfIp;
    private final JTextField jtfPort;
    private final JToggleButton jtbConnect;
    private final AppProperties props;
    private Client client;

    public ClientPreferences(AppProperties props){
        this.props = props;

        this.jlbGuest = new JLabel("Guest:");
        this.jlbUnable = new JLabel("Guest Mode unable while server is up.");
        this.controlPanel = new JPanel();
        this.jtfIp = new JTextField();
        TextPrompt placeholderIp = new TextPrompt("Ip: 127.0.0.1", jtfIp);
        placeholderIp.changeAlpha(0.75f);
        placeholderIp.changeStyle(Font.ITALIC);
        this.jtfPort = new JTextField();
        TextPrompt placeholderPort = new TextPrompt("Port: 81", jtfPort);
        placeholderPort.changeAlpha(0.75f);
        placeholderPort.changeStyle(Font.ITALIC);
        this.jtbConnect = new JToggleButton("Connect");

        initPanelProperties();
        activateListeners();
    }

    public void setContentComponents() {
        this.removeAll();
        if(false){
            this.add(jlbGuest, BorderLayout.WEST);
            this.add(jlbUnable,BorderLayout.CENTER);
        }else{
            this.controlPanel.setLayout(new BorderLayout());
            this.add(jlbGuest, BorderLayout.WEST);
            this.controlPanel.add(jtfIp,BorderLayout.CENTER);
            this.controlPanel.add(jtfPort,BorderLayout.EAST);
            this.add(controlPanel,BorderLayout.CENTER);
            this.add(jtbConnect,BorderLayout.EAST);
        }
    }

    private void initPanelProperties() {
        this.setLayout(new BorderLayout());
    }

    private void activateListeners() {
        this.jtbConnect.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(jtbConnect.isSelected()){
                    String ip = jtfIp.getText();
                    int port = Integer.parseInt(jtfPort.getText());
                    client = new Client(ip, port);
                    if(client.isConnected()) {
                        client.start();
                        jtbConnect.setText("Connected");
                    }else{
                        System.out.println("Server not found");
                    }
                }else{
                    jtbConnect.setText("Connect");
                }
            }
        });
    }


}
