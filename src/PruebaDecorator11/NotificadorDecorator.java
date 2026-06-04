/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebaDecorator11;

/**
 *
 * @author Jean
 */
public class NotificadorDecorator implements Notificador{
    protected Notificador envoltorio;
    
    public NotificadorDecorator(Notificador notificador) {
        this.envoltorio = notificador;
    }
    
    @Override
    public void enviar(String mensaje) {
        this.envoltorio.enviar(mensaje);
    }
    
    
    
}
