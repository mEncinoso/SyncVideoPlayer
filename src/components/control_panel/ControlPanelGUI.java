package components.control_panel;

import app_config_handlers.AppProperties;
import app_config_handlers.GraphicalComponentsAccess;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import java.awt.BorderLayout;

public class ControlPanelGUI extends JPanel {
    private final JSlider jsTimeBar;
    private final JSlider jsVolumeBar;
    private final JButton jbPlay;

    public ControlPanelGUI(AppProperties props) {
        super(new BorderLayout());

        this.jbPlay = new JButton("Play");
        this.jsTimeBar = new JSlider();
        this.jsVolumeBar = new JSlider();

        new ControlPanel(jbPlay, jsTimeBar, jsVolumeBar, props);

        setContentComponents();
        setUpGCA();
    }

    public JSlider getJsTimeBar() {
        return jsTimeBar;
    }

    public JSlider getJsVolumeBar() {
        return jsVolumeBar;
    }

    public JButton getJbPlay() {
        return jbPlay;
    }

    private void setUpGCA() {
        GraphicalComponentsAccess.setControlPanelGUI(this);
    }

    private void setContentComponents() {
        this.add(jbPlay, BorderLayout.WEST);
        this.jbPlay.setEnabled(false);
        this.add(jsTimeBar, BorderLayout.CENTER);
        this.jsTimeBar.setEnabled(false);
        this.add(jsVolumeBar, BorderLayout.EAST);
    }


}
