package components.menu_bar;

import app_config_handlers.GraphicalComponentsAccess;
import app_config_handlers.MimeTypeTikaHandler;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.io.File;

public class MenuBar {

    private final JMenuItem jmiSelectFile;
    private final JMenuItem jmiPreferences;

    public MenuBar(JMenuItem jmiSelectFile, JMenuItem jmiPreferences) {
        this.jmiSelectFile = jmiSelectFile;
        this.jmiPreferences = jmiPreferences;

        activateListeners();
    }

    private void activateListeners() {
        this.jmiSelectFile.addActionListener(e -> {
            JFileChooser jfVideoSelector = new JFileChooser();
            int selected = jfVideoSelector.showOpenDialog(null);
            if (selected == JFileChooser.APPROVE_OPTION) {
                File file = jfVideoSelector.getSelectedFile();
                String path = file.getAbsolutePath();
                String mimeType = MimeTypeTikaHandler.getMimeFileType(file);
                if (mimeType.equals("video") || mimeType.equals("audio")) {
                    GraphicalComponentsAccess.getVideoPlayer().setVideo(path);
                } else {
                    String msg = "El fichero seleccionado puede no tener el "
                            + "formato correcto o no ser un vídeo o audio.\n Archivo: " + path;
                    JOptionPane.showMessageDialog(new JFrame(), msg, "Dialog",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.jmiPreferences.addActionListener(e -> GraphicalComponentsAccess.getPreferencesDialogGUI().setVisible(true));
    }


}
