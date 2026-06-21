package config_Singleton;

import java.time.LocalDateTime;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class LoggerSistema {
    private static LoggerSistema instancia;
    private LoggerSistema() {}

    public static LoggerSistema getInstancia() {
        if (instancia == null) {
            instancia = new LoggerSistema();
        }
        return instancia;
    }

    public void registerLog(String mensaje){
        System.out.println(LocalDateTime.now() + " | " + mensaje);
    }
    
    public void error(String mensaje) {
        System.out.println("[ERROR] " + mensaje);
    }
}
