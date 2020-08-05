package app_config_handlers;

import components.main_frame.MainFrameGUI;

public class App {

    public static void main(String[] args) {
        AppProperties props = new AppProperties();
        new MainFrameGUI(props);
    }
}
