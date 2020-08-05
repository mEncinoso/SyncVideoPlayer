package app_config_handlers;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.formdev.flatlaf.FlatLaf.updateUI;

public abstract class LaFManager {

    private static final String configPath = "C:\\Users\\Rockm\\Documents\\SyncVideoPlayer\\config\\config.txt";

    public static void setTheme(String selectedTheme) throws UnsupportedLookAndFeelException {
            switch (selectedTheme) {
                case "FlatLightLaf" -> UIManager.setLookAndFeel(new FlatLightLaf()) ;
                case "FlatDarkLaf" -> UIManager.setLookAndFeel(new FlatDarkLaf());
                case "FlatIntelliJLaf" -> UIManager.setLookAndFeel(new FlatIntelliJLaf());
                case "FlatDarculaLaf" -> UIManager.setLookAndFeel(new FlatDarculaLaf());
                default -> UIManager.setLookAndFeel(new MetalLookAndFeel());
            }
            updateUI();
    }

    public static void writeThemeToFile(String theme) {
        BufferedReader reader = null;
        String newLine;
        String fileResult = "";
        try {
            reader = new BufferedReader(
                    new FileReader(configPath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("defaultTheme:")) {
                    newLine = line.replaceAll(line, "defaultTheme:" + theme + "\r\n");
                } else {
                    newLine = line + "\r\n";
                }
                fileResult += newLine;
            }
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(configPath);
            fileOut.write(fileResult.getBytes());
            fileOut.close();
            System.out.println("Theme saved in config file!");
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String[] getThemes() {
        String[] themes = new String[0];
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader(configPath));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("themes:")) {
                    themes = line.split(":");
                    themes = themes[1].split(",");
                    for (int i = 0; i < themes.length; i++) {
                        themes[i] = themes[i].trim();
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return themes;
    }

}

