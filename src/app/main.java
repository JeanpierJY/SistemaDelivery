package app;

import DAO_SOLID.IUsuarioDAO;
import DAO_SOLID.UsuarioDAOPostgres;
import DAO_SOLID.IProductoDAO;
import DAO_SOLID.ProductoDAOPostgres;
import DAO_SOLID.IPedidoDAO;
import DAO_SOLID.PedidoDAOPostgres;
import controladores_GRASP.ControladorPedido;
import controladores_GRASP.ControladorUsuario;
import config_Singleton.LoggerSistema;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import logicaDescuentos_Decorator.CompraBase;
import logicaDescuentos_Decorator.DescuentoEstudiante;
import logicaDescuentos_Decorator.DescuentoViernes;
import logicaDescuentos_Decorator.PedidoBase;
import modeloEntrega_Factory.EntregaFactory;
import modeloEntrega_Factory.TipoEntrega;
import modelos.DetallePedido;
import modelos.Pedido;
import modelos.Producto;
import modelos.Repartidor;

public class main {
    private static IUsuarioDAO usuarioDAO = new UsuarioDAOPostgres();
    private static IProductoDAO productoDAO = new ProductoDAOPostgres();
    private static IPedidoDAO pedidoDAO = new PedidoDAOPostgres();
    private static ControladorPedido ctrlPedido = new ControladorPedido();
    private static ControladorUsuario ctrlUsuario = new ControladorUsuario();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("=================================================");
        System.out.println("   SISTEMA DELIVERY - ENTORNO DE INTEGRACION     ");
        System.out.println("=================================================");

        do {
            System.out.println("\n--- SELECCION DE ENTORNO ---");
            System.out.println("1. Modulo Administrador");
            System.out.println("2. Modulo Cliente");
            System.out.println("3. Modulo Empleado (Central)");
            System.out.println("4. Modulo Repartidor");
            System.out.println("5. Apagar Sistema");
            System.out.print("Seleccione un modulo: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                opcion = 0;
            }

            switch (opcion) {
                case 1:
                    menuAdministrador(scanner);
                    break;
                case 2:
                    menuCliente(scanner);
                    break;
                case 3:
                    menuEmpleado(scanner);
                    break;
                case 4:
                    menuRepartidor(scanner);
                    break;
            }
        } while (opcion != 5);
        
        System.out.println("Sistema apagado.");
        scanner.close();
    }

    // ==========================================================
    // MENU 1: ADMINISTRADOR
    // ==========================================================
    private static void menuAdministrador(Scanner scanner) {
        int idAdmin = 1; // ID simulado del admin semilla
        int opcion = 0;
        
        do {
            System.out.println("\n--- PANEL ADMINISTRADOR ---");
            System.out.println("1. Registrar Repartidor (Prueba de APIs Externas)");
            System.out.println("2. Inhabilitar Usuario (Prueba Borrado Logico)");
            System.out.println("3. Salir al Menu Principal");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) {}

            if (opcion == 1) {
                System.out.println("\n--- NUEVO REPARTIDOR ---");
                Repartidor nuevo = new Repartidor();
                System.out.print("Ingrese DNI real (Para API json.pe): ");
                nuevo.setDni(scanner.nextLine());
                System.out.print("Ingrese Placa (Para API json.pe): ");
                nuevo.setPlacaMoto(scanner.nextLine());
                System.out.print("Ingrese Email para el sistema: ");
                nuevo.setEmail(scanner.nextLine());
                
                // Datos por defecto para agilizar la prueba
                nuevo.setNombre("Prueba API");
                nuevo.setTelefono("999999999");
                nuevo.setRol("REPARTIDOR");
                
                System.out.println("Validando documentos en la red...");
                // Aqui el controlador evalua el JSON internamente y protege la BD
                if(ctrlUsuario.registrarNuevoRepartidor(nuevo, "12345")) {
                    System.out.println("-> EXITO: Repartidor registrado en PostgreSQL.");
                    LoggerSistema.getInstancia().guardarLogBD(idAdmin, "Registro repartidor DNI: " + nuevo.getDni());
                } else {
                    System.out.println("-> BLOQUEADO: Las APIs rechazaron el DNI/MTC/SOAT.");
                }
                
            } else if (opcion == 2) {
                System.out.print("\nID del usuario a inhabilitar: ");
                int idEliminar = Integer.parseInt(scanner.nextLine());
                if(usuarioDAO.eliminarUsuario(idEliminar)){
                    System.out.println("-> EXITO: Cuenta desactivada (activo = false).");
                    LoggerSistema.getInstancia().guardarLogBD(idAdmin, "Inhabilito al usuario ID: " + idEliminar);
                } else {
                    System.out.println("-> FALLO: Verifique el ID.");
                }
            }
        } while (opcion != 3);
    }

    // ==========================================================
    // MENU 2: CLIENTE
    // ==========================================================
    private static void menuCliente(Scanner scanner) {
        int idCliente = 3; // ID simulado del cliente semilla
        CompraBase pedidoActual = null;
        TipoEntrega entregaActual = null;
        List<DetallePedido> carritoBD = new ArrayList<>();
        double subtotalPuro = 0.0;
        int opcion = 0;
        
        do {
            System.out.println("\n--- APP CLIENTE ---");
            System.out.println("1. Ver Catalogo y Anadir Producto");
            System.out.println("2. Aplicar cupon (Patron Decorator)");
            System.out.println("3. Elegir tipo envio (Patron Factory)");
            System.out.println("4. Pagar (Patron Observer)");
            System.out.println("5. Salir al Menu Principal");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) {}

            switch (opcion) {
                case 1:
                    List<Producto> catalogo = productoDAO.obtenerCatalogo();
                    for (Producto p : catalogo) {
                        System.out.println("[" + p.getIdProducto() + "] " + p.getNombre() + " - S/ " + p.getPrecio());
                    }
                    System.out.print("\nID del producto: ");
                    int idProd = Integer.parseInt(scanner.nextLine());
                    System.out.print("Cantidad: ");
                    int cant = Integer.parseInt(scanner.nextLine());
                    
                    for (Producto p : catalogo) {
                        if (p.getIdProducto() == idProd) {
                            DetallePedido dp = new DetallePedido();
                            dp.setIdProducto(p.getIdProducto());
                            dp.setCantidad(cant);
                            dp.setPrecioUnitario(p.getPrecio());
                            carritoBD.add(dp);
                            subtotalPuro += (p.getPrecio() * cant);
                            System.out.println("-> Anadido al carrito.");
                            break;
                        }
                    }
                    pedidoActual = new PedidoBase(subtotalPuro);
                    break;
                    
                case 2:
                    if (pedidoActual == null) break;
                    System.out.println("Cupones: [1] Estudiante (-15%) [2] Viernes (-10%)");
                    String desc = scanner.nextLine();
                    if (desc.equals("1")) pedidoActual = new DescuentoEstudiante(pedidoActual);
                    else if (desc.equals("2")) pedidoActual = new DescuentoViernes(pedidoActual);
                    System.out.println("-> Total recalculado.");
                    break;
                    
                case 3:
                    System.out.println("Tipos: EXPRESS, PROGRAMADO, RECOJO EN TIENDA");
                    String tipoEnvio = scanner.nextLine();
                    try {
                        entregaActual = EntregaFactory.crearEntrega(tipoEnvio);
                        entregaActual.tipoEntrega(); 
                    } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
                    break;
                    
                case 4:
                    if (pedidoActual == null || carritoBD.isEmpty()) {
                        System.out.println("Carrito vacio.");
                        break;
                    }
                    double costoEnvio = (entregaActual != null) ? entregaActual.getCosto() : 0.0;
                    double totalFinal = pedidoActual.getTotal() + costoEnvio;
                    
                    System.out.println("\n====== BOLETA ======");
                    System.out.println(pedidoActual.getDescripcion());
                    System.out.println("Subtotal descontado: S/ " + pedidoActual.getTotal());
                    System.out.println("Costo de envio: S/ " + costoEnvio);
                    System.out.println("TOTAL A PAGAR: S/ " + totalFinal);
                    System.out.print("¿Confirmar compra? (S/N): ");
                    
                    if (scanner.nextLine().equalsIgnoreCase("S")) {
                        Pedido nuevoPedido = new Pedido();
                        nuevoPedido.setIdCliente(idCliente);
                        nuevoPedido.setEstado("PENDIENTE");
                        nuevoPedido.setCostoEnvio(costoEnvio);
                        nuevoPedido.setTotal(totalFinal);
                        nuevoPedido.setDetalles(carritoBD);
                        
                        // Guardado transaccional y disparo de notificaciones
                        if (ctrlPedido.guardarYNotificar(nuevoPedido)) {
                            System.out.println("-> EXITO: Compra registrada.");
                            LoggerSistema.getInstancia().guardarLogBD(idCliente, "Realizo una compra de S/" + totalFinal);
                            carritoBD.clear();
                            subtotalPuro = 0.0;
                            pedidoActual = null;
                        } else {
                            System.out.println("-> FALLO: Revertido por seguridad.");
                        }
                    }
                    break;
            }
        } while (opcion != 5);
    }

    // ==========================================================
    // MENU 3: EMPLEADO
    // ==========================================================
    private static void menuEmpleado(Scanner scanner) {
        int idEmpleado = 5; // ID simulado del empleado semilla
        int opcion = 0;
        
        do {
            System.out.println("\n--- TERMINAL EMPLEADO ---");
            System.out.println("1. Ver pedidos PENDIENTES");
            System.out.println("2. Asignar Repartidor");
            System.out.println("3. Salir al Menu Principal");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) {}

            if (opcion == 1) {
                List<Pedido> pendientes = pedidoDAO.obtenerPedidosPorEstado("PENDIENTE");
                if (pendientes.isEmpty()) System.out.println("Todo limpio.");
                for (Pedido p : pendientes) {
                    System.out.println("Pedido ID: " + p.getIdPedido() + " | Fecha: " + p.getFechaRegistro());
                    p.mostrarEstadoActual(); 
                }
            } else if (opcion == 2) {
                System.out.print("Ingrese ID del Pedido: ");
                int idPed = Integer.parseInt(scanner.nextLine());
                System.out.print("Ingrese ID del Repartidor (Ej: 2): ");
                int idRep = Integer.parseInt(scanner.nextLine());
                
                if (pedidoDAO.asignarRepartidor(idPed, idRep)) {
                    // Transicion de Estado y Notificacion automatica
                    ctrlPedido.actualizarEstadoYNotificar(idPed, "EN CURSO");
                    System.out.println("-> EXITO: Pedido en ruta.");
                    LoggerSistema.getInstancia().guardarLogBD(idEmpleado, "Asigno el pedido " + idPed + " al repartidor " + idRep);
                } else {
                    System.out.println("-> FALLO: Verifique IDs.");
                }
            }
        } while (opcion != 3);
    }

    // ==========================================================
    // MENU 4: REPARTIDOR
    // ==========================================================
    private static void menuRepartidor(Scanner scanner) {
        int idRepartidor = 2; // ID simulado del repartidor semilla
        int opcion = 0;
        
        do {
            System.out.println("\n--- APP REPARTIDOR ---");
            System.out.println("1. Ver mis rutas asignadas (EN CURSO)");
            System.out.println("2. Reportar incidencia en ruta");
            System.out.println("3. Marcar pedido como COMPLETADO");
            System.out.println("4. Salir al Menu Principal");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) {}

            if (opcion == 1) {
                List<Pedido> enCurso = pedidoDAO.obtenerPedidosPorEstado("EN CURSO");
                boolean tiene = false;
                for (Pedido p : enCurso) {
                    if (p.getIdRepartidor() != null && p.getIdRepartidor() == idRepartidor) {
                        System.out.println("Pedido ID: " + p.getIdPedido() + " | Cobrar al cliente: S/" + p.getTotal());
                        tiene = true;
                    }
                }
                if (!tiene) System.out.println("Sin rutas pendientes.");
                
            } else if (opcion == 2) {
                System.out.print("Ingrese ID del pedido con problemas: ");
                int idPed = Integer.parseInt(scanner.nextLine());
                System.out.print("Describa el problema: ");
                String problema = scanner.nextLine();
                
                LoggerSistema.getInstancia().guardarLogBD(idRepartidor, "INCIDENCIA [Pedido " + idPed + "]: " + problema);
                System.out.println("-> EXITO: Central notificada. Incidencia guardada.");
                
            } else if (opcion == 3) {
                System.out.print("Ingrese ID del Pedido entregado: ");
                int idPed = Integer.parseInt(scanner.nextLine());
                
                if (ctrlPedido.actualizarEstadoYNotificar(idPed, "COMPLETADO")) {
                    System.out.println("-> EXITO: Mision cumplida. Notificacion enviada.");
                    LoggerSistema.getInstancia().guardarLogBD(idRepartidor, "Entrego el pedido " + idPed);
                } else {
                    System.out.println("-> FALLO: Verifique el ID.");
                }
            }
        } while (opcion != 4);
    }
}