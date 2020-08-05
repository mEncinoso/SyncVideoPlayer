package app_config_handlers;

import components.preferences_dialog.PreferencesDialogGUI;
import components.control_panel.ControlPanelGUI;
import components.main_frame.MainFrameGUI;
import components.menu_bar.MenuBarGUI;
import components.video_player.VideoPlayer;


public abstract class GraphicalComponentsAccess {

    private static MainFrameGUI mainFrameGUI;
    private static VideoPlayer videoPlayerGUI;
    private static ControlPanelGUI controlPanelGUI;
    private static MenuBarGUI menuBarGUI;
    private static PreferencesDialogGUI preferencesDialogGUI;

    public static void setMainFrame(MainFrameGUI mainFrame) {
        mainFrameGUI = mainFrame;
    }

    public static MainFrameGUI getMainFrame() {
        return mainFrameGUI;
    }

    public static void setVideoPlayer(VideoPlayer videoPlayer) {
        videoPlayerGUI = videoPlayer;
    }

    public static VideoPlayer getVideoPlayer() {
        return videoPlayerGUI;
    }

    public static void setControlPanelGUI(ControlPanelGUI controlPanel) {
        controlPanelGUI = controlPanel;
    }

    public static ControlPanelGUI getControlPanelGUI() {
        return controlPanelGUI;
    }

    public static void setMenuBar(MenuBarGUI menu) {
        menuBarGUI = menu;
    }

    public static MenuBarGUI getMenuBarGUI() {
        return menuBarGUI;
    }

    public static PreferencesDialogGUI getPreferencesDialogGUI() {
        return preferencesDialogGUI;
    }

    public static void setPreferencesDialogGUI(PreferencesDialogGUI preferencesDialogGUI) {
        GraphicalComponentsAccess.preferencesDialogGUI = preferencesDialogGUI;
    }



}
