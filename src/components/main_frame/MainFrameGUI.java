package components.main_frame;

import app_config_handlers.AppProperties;
import app_config_handlers.GraphicalComponentsAccess;
import app_config_handlers.LaFManager;
import components.control_panel.ControlPanelGUI;
import components.menu_bar.MenuBarGUI;
import components.preferences_dialog.PreferencesDialogGUI;
import components.video_player.VideoPlayerGUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class MainFrameGUI extends JFrame {
    private final AppProperties props;
    private final JPanel container;
    private final MenuBarGUI menuBar;
    private final VideoPlayerGUI videoPanelGUI;
    private final ControlPanelGUI controlPanelGUI;
    private final PreferencesDialogGUI preferencesDialogGUI;

    public MainFrameGUI(AppProperties props) {
        super("SyncVideoPlayer");
        try {
            LaFManager.setTheme(props.getDefaultTheme());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.props = props;
        this.container = new JPanel();
        this.menuBar = new MenuBarGUI();
        this.videoPanelGUI = new VideoPlayerGUI(this, props);
        this.controlPanelGUI = new ControlPanelGUI(props);

        initFrameProperties();
        setContentComponents();
        linkComponents();
        setUpGCA();
        this.preferencesDialogGUI = new PreferencesDialogGUI(props);

        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void setUpGCA() {
        GraphicalComponentsAccess.setMainFrame(this);
    }

    private void linkComponents() {
        GraphicalComponentsAccess.getVideoPlayer().linkComponents();
    }

    private void setContentComponents() {
        this.setContentPane(this.container);
        this.container.setLayout(new BorderLayout());
        this.setJMenuBar(this.menuBar);
        this.container.add(this.videoPanelGUI,BorderLayout.CENTER);
        this.container.add(this.controlPanelGUI,BorderLayout.SOUTH);
        this.pack();
    }

    private void initFrameProperties() {
        this.setPreferredSize(new Dimension(props.getDefaultFrameHeight(), props.getDefaultFrameWidth()));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
