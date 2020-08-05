package components.control_panel;

import app_config_handlers.AppProperties;
import app_config_handlers.GraphicalComponentsAccess;
import app_config_handlers.server_client.ServerClientAccess;

import javax.swing.JButton;
import javax.swing.JSlider;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ControlPanel {

    private final JSlider jsTimeBar;
    private final JSlider jsVolumeBar;
    private final JButton jbPlay;
    private final AppProperties props;

    public ControlPanel(JButton jbPlay, JSlider jsTimeBar, JSlider jsVolumeBar, AppProperties props) {
        this.props = props;
        this.jbPlay = jbPlay;
        this.jsTimeBar = jsTimeBar;
        this.jsVolumeBar = jsVolumeBar;

        activateListeners();
    }

    private void activateListeners() {

        jbPlay.addActionListener(e -> {
            GraphicalComponentsAccess.getVideoPlayer().play();
            try {
                if (props.isServer()) {
                    if (ServerClientAccess.getServer().isConnected()) {
                        ServerClientAccess.getServer().sendMessage("play:");
                    }
                } else {
                    if (ServerClientAccess.getClient().isConnected()) {
                        ServerClientAccess.getClient().sendMessage("play:");
                    }
                }
            } catch (NullPointerException x) {
                System.out.println("Connection is not stated.");
            }
        });

        jsVolumeBar.addChangeListener(e -> GraphicalComponentsAccess.getVideoPlayer().setVolume(jsVolumeBar.getValue()));

        jsTimeBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
                int time = jsTimeBar.getValue() * 1000;
                GraphicalComponentsAccess.getVideoPlayer().setTime(time);
                try {
                    if (props.isServer()) {
                        if (ServerClientAccess.getServer().isConnected()) {
                            ServerClientAccess.getServer().sendMessage("time:" + time);
                        }
                    } else {
                        if (ServerClientAccess.getClient().isConnected()) {
                            ServerClientAccess.getClient().sendMessage("time:" + time);
                        }
                    }
                } catch (NullPointerException e) {
                    System.out.println("Connection is not stated.");
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }
        });

    }

}
