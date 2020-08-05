package components.video_player;

import app_config_handlers.AppProperties;
import app_config_handlers.server_client.ServerClientAccess;
import com.sun.jna.NativeLibrary;
import app_config_handlers.GraphicalComponentsAccess;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.embedded.EmbeddedMediaPlayer;
import uk.co.caprica.vlcj.player.embedded.videosurface.CanvasVideoSurface;
import uk.co.caprica.vlcj.player.embedded.windows.Win32FullScreenStrategy;
import uk.co.caprica.vlcj.runtime.RuntimeUtil;

import javax.swing.*;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Thread.sleep;

public class VideoPlayer implements Runnable {
    private final EmbeddedMediaPlayer mediaPlayer;
    private Thread videoThread;

    private JSlider jsTimeBar;
    private JSlider jsVolumeBar;
    private JButton jbPlay;

    private final AppProperties props;

    public VideoPlayer(JFrame mainFrame, Canvas videoCanvas, AppProperties props) {
        this.props = props;

        NativeLibrary.addSearchPath(RuntimeUtil.getLibVlcLibraryName(), "C:\\Program Files\\VideoLAN\\VLC");
        MediaPlayerFactory mediaPlayerFactory = new MediaPlayerFactory();
        CanvasVideoSurface videoSurface = mediaPlayerFactory.newVideoSurface(videoCanvas);
        this.mediaPlayer = mediaPlayerFactory.newEmbeddedMediaPlayer(new Win32FullScreenStrategy(mainFrame));
        this.mediaPlayer.setVideoSurface(videoSurface);
        this.mediaPlayer.setEnableMouseInputHandling(false);
        this.mediaPlayer.setEnableKeyInputHandling(false);

        activateListeners(videoCanvas);
    }

    private void activateListeners(Canvas videoCanvas) {
        videoCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                if (mediaPlayer.isPlayable()) {
                    if (event.getClickCount() == 2) {
                        mediaPlayer.toggleFullScreen();
                        if (mediaPlayer.isFullScreen()) {
                            GraphicalComponentsAccess.getControlPanelGUI().setVisible(false);
                            GraphicalComponentsAccess.getMenuBarGUI().setVisible(false);
                        } else {
                            GraphicalComponentsAccess.getControlPanelGUI().setVisible(true);
                            GraphicalComponentsAccess.getMenuBarGUI().setVisible(true);
                        }
                    } else if (event.getClickCount() == 1) {
                        play();
                        try {
                            if (props.isServer()) {
                                if (ServerClientAccess.getServer().isConnected()) {
                                    ServerClientAccess.getServer().sendMessage("play:");
                                }
                            } else {
                                if (ServerClientAccess.getClient().isConnected()) {
                                    ServerClientAccess.getClient().sendMessage("play:");
                                }
                            }
                        } catch (NullPointerException x) {
                            System.out.println("Connection is not stated.");
                        }
                    }
                }
            }
        });
    }

    public void startTimeUpdater() {
        videoThread = new Thread(this);
        videoThread.start();
    }

    public void setVideo(String path) {
        jbPlay.setText("Play");
        mediaPlayer.playMedia(path);
        jsTimeBar.setEnabled(true);
        jbPlay.setEnabled(true);

        try {
            sleep(500);
            mediaPlayer.pause();
            jsTimeBar.setMinimum(0);
            jsTimeBar.setMaximum((int) (mediaPlayer.getLength() / 1000));
            mediaPlayer.setVolume(jsVolumeBar.getValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (mediaPlayer.isPlaying()) {
            int time = (int) mediaPlayer.getTime() / 1000;
            this.jsTimeBar.setValue(time);
            this.jsTimeBar.setToolTipText("" + time);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(VideoPlayer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void play() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.play();
            this.startTimeUpdater();
            jbPlay.setText("Pause");
        } else {
            mediaPlayer.pause();
            jbPlay.setText("Play");
        }
    }

    public void setVolume(int volume) {
        mediaPlayer.setVolume(volume);
        jsVolumeBar.setToolTipText("" + volume);
    }

    public void linkComponents() {
        this.jsTimeBar = GraphicalComponentsAccess.getControlPanelGUI().getJsTimeBar();
        this.jsVolumeBar = GraphicalComponentsAccess.getControlPanelGUI().getJsVolumeBar();
        this.jbPlay = GraphicalComponentsAccess.getControlPanelGUI().getJbPlay();
    }

    public void setTime(int time) {
        mediaPlayer.setTime(time);
    }
}
