package app_config_handlers;

import org.apache.tika.Tika;

import java.io.File;
import java.io.IOException;

public abstract class MimeTypeTikaHandler {

    public static String getMimeFileType(File file) {
        Tika tika = new Tika();
        String mimeType = "";
        try {
            mimeType = tika.detect(file);
            mimeType = mimeType.substring(0, mimeType.indexOf("/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mimeType;
    }

}
