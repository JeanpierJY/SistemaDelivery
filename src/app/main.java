package app;

import config.ConfiguracionGlobal;
import config.LoggerSistema;
import config.SeguridadSistema;
import database.ConexionBD;

import factory.FactoryEntrega;
import modelo.entrega.ITipoEntrega;
import factory.FactoryNotificacion;
import modelo.notificacion.IMedioNotificacion;

public class main {
    public static void main(String[] args) {
        
        //singleton: instancia de objetos
        ConfiguracionGlobal config = ConfiguracionGlobal.getInstancia();
        LoggerSistema logger = LoggerSistema.getInstancia();
        SeguridadSistema seguridad = SeguridadSistema.getInstancia();
        ConexionBD conexion = ConexionBD.getInstancia();
        
        //configuracion
        System.out.println("Aplicación: " + config.getNombreApp());
        System.out.println("Versión: " + config.getVersion());

        // logger
        logger.registerLog("Sistema iniciado correctamente");

        // seguridad
        boolean permitido = seguridad.tienePermiso("CLIENTE","CREAR_PEDIDO");

        System.out.println("¿Cliente puede crear pedido?: "+ permitido);

        // factory
        ITipoEntrega entrega =FactoryEntrega.crearEntrega("EXPRESS");
        entrega.programarEntrega();
        System.out.println("Costo entrega: "+ entrega.getCosto());

        IMedioNotificacion notificacion = FactoryNotificacion.crearNotificacion("EMAIL");
        notificacion.enviarMensaje("Tu pedido fue enviado");

        logger.registerLog("Sistema finalizado");
        
        System.out.println("ESTE ES PARA GITHUB");
    }
}
