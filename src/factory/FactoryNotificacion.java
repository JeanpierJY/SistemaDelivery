package factory;

import modelo.notificacion.Email;
import modelo.notificacion.IMedioNotificacion;
import modelo.notificacion.SMS;
import modelo.notificacion.WhatsApp;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class FactoryNotificacion {

    public static IMedioNotificacion crearNotificacion(String tipo) {

        switch(tipo.toUpperCase()) {

            case "SMS":
                return new SMS();

            case "EMAIL":
                return new Email();

            case "WHATSAPP":
                return new WhatsApp();

            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
