package components.video_player;

import app_config_handlers.AppProperties;
import app_config_handlers.FileDropHandler;
import app_config_handlers.GraphicalComponentsAccess;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Canvas;

public class VideoPlayerGUI extends JPanel {

    private final Canvas videoCanvas;
    private final VideoPlayer videoPlayer;

    public VideoPlayerGUI(JFrame mainFrame, AppProperties props) {
        super();
        videoCanvas = new Canvas();
        this.videoPlayer = new VideoPlayer(mainFrame, videoCanvas, props);
        this.setTransferHandler(new FileDropHandler());

        initPanelProperties();
        setContentComponents();
        setUpGCA();
    }

    private void setContentComponents() {
        this.setLayout(new BorderLayout());
        this.add(videoCanvas, BorderLayout.CENTER);
    }

    private void initPanelProperties() {
        videoCanvas.setSize(this.getSize());
    }

    private void setUpGCA() {
        GraphicalComponentsAccess.setVideoPlayer(this.videoPlayer);
    }

}
