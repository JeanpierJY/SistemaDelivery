package config_Singleton;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class ConfiguracionGlobal {
    private static ConfiguracionGlobal instancia;

    private String nombreApp;
    private String version;

    private ConfiguracionGlobal() {
        nombreApp = "Sistema Delivery para Restaurante de Pastas";
        version = "1.0";
    }

    public static ConfiguracionGlobal getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionGlobal();
        }
        return instancia;
    }

    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getNombreApp() {
        return nombreApp;
    }

    public String getVersion() {
        return version;
    }

}