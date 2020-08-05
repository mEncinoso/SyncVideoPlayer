package components.preferences_dialog;

import app_config_handlers.AppProperties;
import app_config_handlers.GraphicalComponentsAccess;
import components.preferences_dialog.preferences_options.ClientPreferences;
import components.preferences_dialog.preferences_options.ServerPreferences;
import components.preferences_dialog.preferences_options.ThemePreferences;

import javax.swing.*;
import java.awt.*;

public class PreferencesDialogGUI extends JDialog {

    private final JPanel container;
    private final JPanel optionsIndex;
    private final JPanel optionsIndexList;
    private final JPanel optionsConfig;
    private final JSeparator separator;
    private final ButtonGroup buttonGroup;
    private final JToggleButton jtbThemes;
    private final JToggleButton jtbHost;
    private final JToggleButton jtbGuest;
    private final JToggleButton jtbLanguage;
    private final ThemePreferences themePreferences;
    private final ServerPreferences serverPreferences;
    private final ClientPreferences clientPreferences;

    public PreferencesDialogGUI(AppProperties props) {
        super();
        this.container = new JPanel();
        this.optionsIndex = new JPanel();
        this.optionsIndexList = new JPanel();
        this.separator = new JSeparator(SwingConstants.VERTICAL);

        this.optionsConfig = new JPanel();

        //optionsConfig.setBorder(BorderFactory.createLineBorder(Color.black));

        //OptionButtons
        this.buttonGroup = new ButtonGroup();
        this.jtbThemes = new JToggleButton("Themes");
        this.jtbHost = new JToggleButton("Server");
        this.jtbGuest = new JToggleButton("Guest");
        this.jtbLanguage = new JToggleButton("Language");

        //OptionPanels
        this.themePreferences = new ThemePreferences();
        this.serverPreferences = new ServerPreferences(props);
        this.clientPreferences = new ClientPreferences(props);


        initDialogProperties();
        setContentComponents();
        setToggleButtonsProperties();
        activateListeners();
        setUpGCA();
    }

    private void activateListeners() {
        this.jtbThemes.addActionListener(e -> {
            if (jtbThemes.isSelected()) paintOptionConfig("themes");
        });

        this.jtbHost.addActionListener(e -> {
            if (jtbHost.isSelected()) paintOptionConfig("host");
        });

        this.jtbGuest.addActionListener(e -> {
            if (jtbGuest.isSelected()) paintOptionConfig("guest");
        });

    }

    private void paintOptionConfig(String option) {
        this.optionsConfig.setVisible(false);
        this.optionsConfig.removeAll();
        switch (option) {
            case "themes" -> {
                formatOptionsGrid(8,2);
                this.optionsConfig.setLayout(new GridLayout());
                this.optionsConfig.add(this.themePreferences, BorderLayout.CENTER);

            } case "host" -> {
                formatOptionsGrid(8,2);
                this.optionsConfig.add(this.serverPreferences, BorderLayout.CENTER);
            }
            case "guest" -> {
                formatOptionsGrid(8,3);
                this.optionsConfig.add(this.clientPreferences, BorderLayout.CENTER);
                this.clientPreferences.setContentComponents();
            }
        }
        this.optionsConfig.setVisible(true);
    }

    private void formatOptionsGrid(int rows, int cols) {
        this.optionsConfig.setLayout(new GridLayout(rows,cols,5,5));
    }

    private void setToggleButtonsProperties() {
        int buttonsCount = this.optionsIndexList.getComponentCount();
        for (int i = 0; i < buttonsCount; i++) {
            this.buttonGroup.add((AbstractButton) this.optionsIndexList.getComponent(i));

        }
        this.optionsIndexList.setLayout(new GridLayout(buttonsCount + 5, 1, 0, 1));
    }

    private void setUpGCA() {
        GraphicalComponentsAccess.setPreferencesDialogGUI(this);
    }

    private void setContentComponents() {
        this.setContentPane(container);
        this.container.setLayout(new BorderLayout());
        this.container.add(optionsIndex, BorderLayout.WEST);
        this.optionsIndex.setLayout(new BorderLayout());
        this.optionsIndex.add(optionsIndexList, BorderLayout.CENTER);
        this.optionsIndex.add(separator, BorderLayout.EAST);
        this.optionsIndexList.add(jtbThemes);
        this.optionsIndexList.add(jtbHost);
        this.optionsIndexList.add(jtbGuest);
        this.optionsIndexList.add(jtbLanguage);
        this.container.add(optionsConfig, BorderLayout.CENTER);
        this.pack();
    }

    private void initDialogProperties() {
        int height = GraphicalComponentsAccess.getMainFrame().getHeight() - 200;
        int width = GraphicalComponentsAccess.getMainFrame().getWidth() - 200;
        this.setPreferredSize(new Dimension(height, width));
        this.setLocationRelativeTo(GraphicalComponentsAccess.getMainFrame());
        this.setModal(true);
    }

}
