package modelo.notificacion;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class WhatsApp implements IMedioNotificacion {
    
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando mensaje: " + mensaje);
    }
}
