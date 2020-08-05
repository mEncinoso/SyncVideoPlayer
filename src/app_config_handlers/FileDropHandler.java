package app_config_handlers;

import org.apache.tika.Tika;

import javax.swing.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileDropHandler extends TransferHandler {
    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        for (DataFlavor flavor : support.getDataFlavors()) {
            if (flavor.isFlavorJavaFileListType()) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean importData(TransferHandler.TransferSupport support) {
        if (!this.canImport(support))
            return false;

        List<File> files;
        try {
            files = (List<File>) support.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
        } catch (UnsupportedFlavorException | IOException ex) {
            // should never happen (or JDK is buggy)
            return false;
        }

        for (File file : files) {
            String path = file.getAbsolutePath();
            String mimeType = MimeTypeTikaHandler.getMimeFileType(file);
            if (mimeType.equals("video") || mimeType.equals("audio")) {
                GraphicalComponentsAccess.getVideoPlayer().setVideo(path);
            } else {
                String msg = "El fichero seleccionado puede no tener un "
                        + "formato correcto o no ser un vídeo o audio.\n Archivo: " + path;
                JOptionPane.showMessageDialog(new JFrame(), msg, "Dialog",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return true;
    }

}