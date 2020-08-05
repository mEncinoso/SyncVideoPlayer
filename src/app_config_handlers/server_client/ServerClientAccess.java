package app_config_handlers.server_client;

public abstract class ServerClientAccess {

    private static Server server;
    private static Client client;

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        ServerClientAccess.server = server;
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        ServerClientAccess.client = client;
    }
}
