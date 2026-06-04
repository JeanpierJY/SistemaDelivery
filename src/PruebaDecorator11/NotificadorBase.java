/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebaDecorator11;

/**
 *
 * @author Jean
 */
public class NotificadorBase implements Notificador{

    @Override
    public void enviar(String mensaje) {
        System.out.println("Notificacion guardada");
    }
    
}
