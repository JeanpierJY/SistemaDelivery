package modelo.notificacion;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class SMS implements IMedioNotificacion {

    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando EMAIL" + mensaje);
    }
}