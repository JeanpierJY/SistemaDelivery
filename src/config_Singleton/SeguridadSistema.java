package config_Singleton;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class SeguridadSistema {
    private static SeguridadSistema instancia;
    private Map<String, List<String>> permisos;
    
    private SeguridadSistema() {
        permisos = new HashMap<>();
        
        permisos.put("ADMIN", Arrays.asList(
            "GESTIONAR_CATALOGO",
            "GESTIONAR_USUARIOS",
            "VER_REPORTES"
        ));
        
        permisos.put("REPARTIDOR", Arrays.asList(
            "VER_PEDIDOS_PENDIENTES",
            "ACTUALIZAR_ESTADO_PEDIDO"
        ));

        permisos.put("CLIENTE", Arrays.asList(
            "VER_CATALOGO",
            "CREAR_PEDIDO",
            "VER_HISTORIAL_COMPRAS"
        ));
    }

    public static SeguridadSistema getInstancia() {
        if (instancia == null) {
            instancia = new SeguridadSistema();
        }
        return instancia;
    }

    public boolean tienePermiso(String rol, String permiso) {
        List<String> listaPermisos = permisos.get(rol);

        if (listaPermisos == null) {
            return false;
        }
        return listaPermisos.contains(permiso);
    }
}