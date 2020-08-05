package app_config_handlers;

public class AppProperties {

    private final int defaultFrameHeight;
    private final int defaultFrameWidth;
    private final int defaultVolume;
    private final String defaultLanguage;
    private final String defaultTheme;
    private boolean isServer;

    public AppProperties() {
        ConfigDataHandler.setUpConfigFile();
        this.defaultFrameHeight = Integer.parseInt(ConfigDataHandler.getData("defaultFrameHeight"));
        this.defaultFrameWidth = Integer.parseInt(ConfigDataHandler.getData("defaultFrameWidth"));
        this.defaultVolume = Integer.parseInt(ConfigDataHandler.getData("defaultVolume"));
        this.defaultLanguage = ConfigDataHandler.getData("defaultLanguage");
        this.defaultTheme = ConfigDataHandler.getData("defaultTheme");
        this.isServer = false;
    }

    public int getDefaultFrameHeight() {
        return defaultFrameHeight;
    }

    public int getDefaultFrameWidth() {
        return defaultFrameWidth;
    }

    public int getDefaultVolume() {
        return defaultVolume;
    }

    public String getDefaultLanguage() {
        return defaultLanguage;
    }

    public String getDefaultTheme() {
        return defaultTheme;
    }

    public boolean isServer() {
        return isServer;
    }

    public void setServer(boolean isServer) {
        this.isServer = isServer;
    }

}
