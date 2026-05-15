package config;

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

        permisos.put("ADMIN",
                Arrays.asList(
                        "CREAR_PEDIDO",
                        "CANCELAR_PEDIDO",
                        "GESTIONAR_USUARIOS",
                        "VER_REPORTES"
                ));

        permisos.put("CLIENTE",
                Arrays.asList(
                        "CREAR_PEDIDO",
                        "VER_PEDIDO",
                        "CANCELAR_PEDIDO",
                        "VER_RUTA"
                ));

        permisos.put("REPARTIDOR",
                Arrays.asList(
                        "VER_RUTA",
                        "ACTUALIZAR_ENTREGA"
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