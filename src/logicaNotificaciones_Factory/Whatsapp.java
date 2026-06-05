/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaNotificaciones_Factory;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class Whatsapp implements Notificacion {
  
    @Override
    public void enviarMensaje(String mensaje) {
        System.out.println("Enviando mensaje: " + mensaje);
    }
}
