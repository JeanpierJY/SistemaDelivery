package app;

import config_Singleton.ConfiguracionGlobal;
import config_Singleton.LoggerSistema;
import config_Singleton.SeguridadSistema;
import config_Singleton.ConexionBD;

import Pruebafactory.FactoryEntrega;
import Pruebamodelo.entrega.ITipoEntrega;
import Pruebafactory.FactoryNotificacion;
import Pruebamodelo.notificacion.IMedioNotificacion;

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
