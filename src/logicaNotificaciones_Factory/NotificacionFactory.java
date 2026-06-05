/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNotificaciones_Factory;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class NotificacionFactory {
    
    public static Notificacion crearNotificacion(String tipo) {

        switch(tipo.toUpperCase()) {
            case "SMS":
                return new SMS();
            case "EMAIL":
                return new Email();
            case "WHATSAPP":
                return new Whatsapp();

            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}
