package components.preferences_dialog.preferences_options;

import app_config_handlers.LaFManager;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static app_config_handlers.LaFManager.getThemes;

public class ThemePreferences extends JPanel {

    private final JLabel jlbThemes;
    private JList<Object> jlThemes;

    public ThemePreferences() {
        this.jlbThemes = new JLabel("Themes:");
        listThemes();

        initPanelProperties();
        setContentComponents();
        activateListeners();
    }

    private void listThemes() {
        DefaultListModel<Object> themesModel = new DefaultListModel<>();
        this.jlThemes = new JList<>(themesModel);
        String[] themes = getThemes();
        for (int i = 0; i < themes.length; i++) {
            themesModel.add(i, themes[i]);
        }
    }

    private void activateListeners() {
        jlThemes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                String selectedTheme = jlThemes.getSelectedValue().toString();
                String actualTheme = UIManager.getLookAndFeel().getClass().getName();
                actualTheme = actualTheme.substring(actualTheme.lastIndexOf(".")+1).trim();
                if(!selectedTheme.equals(actualTheme)) {
                    try {
                        LaFManager.setTheme(selectedTheme);
                        LaFManager.writeThemeToFile(selectedTheme);
                    } catch (UnsupportedLookAndFeelException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void initPanelProperties() {
        this.setLayout(new BorderLayout());
    }

    private void setContentComponents() {
        this.add(jlbThemes, BorderLayout.WEST);
        this.add(jlThemes, BorderLayout.CENTER);
    }
}
