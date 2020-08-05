package app_config_handlers;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class ConfigDataHandler {

    private static File configFile = new File("C:\\Users\\Rockm\\Documents\\SyncVideoPlayer\\config\\config.txt");

    public static String getData(String type) {
        String dataResult = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new FileReader(configFile));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(type + ":")) {
                    int separatorIndex = line.indexOf(":") + 1;
                    dataResult = line.substring(separatorIndex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                assert reader != null;
                reader.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return dataResult;
    }

    public static void setUpConfigFile() {
        if (!configFile.exists()) {
            try {
                if (configFile.createNewFile()) {
                    System.out.println("Default Config File created!");
                    writeDefaultData();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void writeDefaultData() {
        String fileResult;
        try {
            fileResult =
                    "#Themes\r\n" +
                    "themes:FlatLightLaf,FlatDarkLaf,FlatIntelliJLaf,FlatDarculaLaf\r\n" +
                    "#Theme\r\n" +
                    "defaultTheme:FlatLightLaf\r\n" +
                    "#FrameHeight\r\n" +
                    "defaultFrameHeight:700\r\n" +
                    "#FrameWidth\r\n" +
                    "defaultFrameWidth:650\r\n" +
                    "#Volume\r\n" +
                    "defaultVolume:100\r\n" +
                    "#Language\r\n" +
                    "defaultLanguage:English\r\n";
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(configFile);
            fileOut.write(fileResult.getBytes());
            fileOut.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
