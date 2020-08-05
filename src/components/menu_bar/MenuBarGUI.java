package components.menu_bar;

import app_config_handlers.GraphicalComponentsAccess;

import javax.swing.*;

public class MenuBarGUI extends JMenuBar {
    private final JMenu jmOptions;
    private final JMenu jmFile;
    private final JMenuItem jmiSelectFile;
    private final JMenuItem jmiPreferences;
    private final MenuBar menuBar;
    //private final PreferencesWindow windowPrefs;

    public MenuBarGUI() {
        super();

        this.jmFile = new JMenu("File");
        this.jmOptions = new JMenu("Options");
        this.jmiSelectFile = new JMenuItem("Select video/audio");
        this.jmiPreferences = new JMenuItem("Preferences");

        this.menuBar = new MenuBar(jmiSelectFile, jmiPreferences);

        setContentComponents();

        setUpGCA();
    }

    private void setContentComponents() {
        this.add(jmFile);
        this.add(jmOptions);
        this.jmFile.add(jmiSelectFile);
        this.jmOptions.add(jmiPreferences);
    }

    private void setUpGCA() {
        GraphicalComponentsAccess.setMenuBar(this);
    }

}
