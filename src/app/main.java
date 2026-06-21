package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import config_Singleton.ConfiguracionGlobal;
import config_Singleton.LoggerSistema;

import modeloEntrega_Factory.EntregaFactory;
import modeloEntrega_Factory.TipoEntrega;
import logicaNotificaciones_Factory.NotificacionFactory;
import logicaNotificaciones_Factory.Notificacion;
import logicaDescuentos_Decorator.CompraBase;
import logicaDescuentos_Decorator.PedidoBase;
import logicaDescuentos_Decorator.DescuentoCumpleaños;
import logicaDescuentos_Decorator.DescuentoEstudiante;
import logicaDescuentos_Decorator.DescuentoViernes;

import DAO_SOLID.IUsuarioDAO;
import DAO_SOLID.UsuarioDAOPostgres;
import DAO_SOLID.IProductoDAO;
import DAO_SOLID.ProductoDAOPostgres;
import DAO_SOLID.IPedidoDAO;
import DAO_SOLID.PedidoDAOPostgres;

import modelos.Persona;
import modelos.Administrador;
import modelos.Empleado;
import modelos.Repartidor;
import modelos.Cliente;
import modelos.Producto;
import modelos.Pedido;
import modelos.DetallePedido;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class main {
    
    private static IUsuarioDAO usuarioDAO = new UsuarioDAOPostgres();
    private static IProductoDAO productoDAO = new ProductoDAOPostgres();
    private static IPedidoDAO pedidoDAO = new PedidoDAOPostgres();
    private static LoggerSistema logger = LoggerSistema.getInstancia();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConfiguracionGlobal config = ConfiguracionGlobal.getInstancia();
        
        System.out.println("=================================================");
        System.out.println(" INICIANDO: " + config.getNombreApp() + " v" + config.getVersion());
        System.out.println("=================================================");

        System.out.println("\n--- LOGIN ---");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Persona usuarioActivo = usuarioDAO.loginUsuario(email, password);

        if (usuarioActivo == null) {
            System.out.println("-> ACCESO DENEGADO.");
            return;
        }

        logger.registerLog("Sesion iniciada por: " + usuarioActivo.getNombre() + " (" + usuarioActivo.getRol() + ")");
        System.out.println("\n-> Bienvenido, " + usuarioActivo.getNombre());

        // ENRUTADOR POLIMORFICO
        if (usuarioActivo instanceof Administrador) {
            menuAdministrador((Administrador) usuarioActivo, scanner);
        } else if (usuarioActivo instanceof Empleado) {
            menuEmpleado((Empleado) usuarioActivo, scanner);
        } else if (usuarioActivo instanceof Repartidor) {
            menuRepartidor((Repartidor) usuarioActivo, scanner);
        } else if (usuarioActivo instanceof Cliente) {
            menuCliente((Cliente) usuarioActivo, scanner);
        }

        System.out.println("Apagando sistema...");
        logger.registerLog("Sesion finalizada.");
        scanner.close();
    }

    // ==========================================================
    // MENU 1: ADMINISTRADOR (Backoffice)
    // ==========================================================
    private static void menuAdministrador(Administrador admin, Scanner scanner) {
        int opcion = 0;
        do {
            System.out.println("\n--- MENU ADMINISTRADOR ---");
            System.out.println("1. Gestionar Catalogo de Productos");
            System.out.println("2. Gestionar Repartidores");
            System.out.println("3. Acceder al Modulo Operativo (Menu Empleado)");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) { opcion = 0; }

            if (opcion == 1) {
                System.out.println("\n[ CATALOGO ACTUAL ]");
                List<Producto> lista = productoDAO.obtenerCatalogo();
                for (Producto p : lista) {
                    System.out.println("[" + p.getIdProducto() + "] " + p.getNombre() + " - S/" + p.getPrecio());
                }
            } else if (opcion == 2) {
                System.out.println("\n[ REPARTIDORES REGISTRADOS ]");
                List<Persona> usuarios = usuarioDAO.obtenerUsuarios();
                for (Persona u : usuarios) {
                    // RETO 2 RESUELTO: Filtrado en memoria usando instanceof
                    if (u instanceof Repartidor) {
                        Repartidor r = (Repartidor) u;
                        System.out.println("ID: " + r.getId() + " | Nombre: " + r.getNombre() + " | Placa: " + r.getPlacaMoto());
                    }
                }
            } else if (opcion == 3) {
                // RETO 1 RESUELTO: Reutilizacion de modulo sin duplicar codigo
                menuEmpleado(new Empleado(), scanner); 
            }
        } while (opcion != 4);
    }

    // ==========================================================
    // MENU 2: EMPLEADO (Operaciones y Logistica)
    // ==========================================================
    private static void menuEmpleado(Empleado empleado, Scanner scanner) {
        int opcion = 0;
        do {
            System.out.println("\n--- MODULO OPERATIVO ---");
            System.out.println("1. Ver pedidos pendientes");
            System.out.println("2. Asignar Repartidor a un pedido");
            System.out.println("3. Volver");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) { opcion = 0; }

            if (opcion == 1) {
                List<Pedido> pendientes = pedidoDAO.obtenerPedidosPorEstado("PENDIENTE");
                if (pendientes.isEmpty()) {
                    System.out.println("No hay pedidos pendientes.");
                } else {
                    for (Pedido p : pendientes) {
                        System.out.println("Pedido ID: " + p.getIdPedido() + " | Total: S/" + p.getTotal());
                    }
                }
            } else if (opcion == 2) {
                // RETO 3 RESUELTO: Algoritmo de asignacion cruzada
                System.out.print("Ingrese ID del Pedido: ");
                int idPed = Integer.parseInt(scanner.nextLine());
                
                System.out.println("[ Repartidores Disponibles ]");
                List<Persona> usuarios = usuarioDAO.obtenerUsuarios();
                for (Persona u : usuarios) {
                    if (u instanceof Repartidor) {
                        System.out.println("ID: " + u.getId() + " - " + u.getNombre());
                    }
                }
                
                System.out.print("Ingrese ID del Repartidor: ");
                int idRep = Integer.parseInt(scanner.nextLine());
                
                if (pedidoDAO.asignarRepartidor(idPed, idRep)) {
                    System.out.println("-> Asignacion exitosa. El pedido ahora esta EN CURSO.");
                } else {
                    System.out.println("-> Error en la asignacion.");
                }
            }
        } while (opcion != 3);
    }

    // ==========================================================
    // MENU 3: REPARTIDOR (Motorizado)
    // ==========================================================
    private static void menuRepartidor(Repartidor repartidor, Scanner scanner) {
        int opcion = 0;
        do {
            System.out.println("\n--- MENU REPARTIDOR ---");
            System.out.println("1. Ver mis pedidos asignados (EN CURSO)");
            System.out.println("2. Marcar pedido como COMPLETADO");
            System.out.println("3. Reportar incidencia");
            System.out.println("4. Salir");
            System.out.print("Opcion: ");
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) { opcion = 0; }

            if (opcion == 1) {
                List<Pedido> enCurso = pedidoDAO.obtenerPedidosPorEstado("EN CURSO");
                boolean tienePedidos = false;
                for (Pedido p : enCurso) {
                    // Solo muestra los que le pertenecen a este repartidor
                    if (p.getIdRepartidor() != null && p.getIdRepartidor() == repartidor.getId()) {
                        System.out.println("Pedido ID: " + p.getIdPedido() + " | A cobrar: S/" + p.getTotal());
                        tienePedidos = true;
                    }
                }
                if (!tienePedidos) System.out.println("No tienes pedidos asignados en curso.");
                
            } else if (opcion == 2) {
                System.out.print("Ingrese ID del Pedido entregado: ");
                int idPed = Integer.parseInt(scanner.nextLine());
                if (pedidoDAO.actualizarEstadoPedido(idPed, "COMPLETADO")) {
                    System.out.println("-> Pedido entregado con exito.");
                }
            } else if (opcion == 3) {
                System.out.print("Describa la incidencia: ");
                String problema = scanner.nextLine();
                logger.registerLog("INCIDENCIA (Rep. " + repartidor.getNombre() + "): " + problema);
                System.out.println("-> Incidencia registrada en el sistema.");
            }
        } while (opcion != 4);
    }

    // ==========================================================
    // MENU 4: CLIENTE (Compras)
    // ==========================================================
    private static void menuCliente(Cliente cliente, Scanner scanner) {
        CompraBase pedidoActual = null;
        TipoEntrega entregaActual = null;
        List<DetallePedido> carritoBD = new ArrayList<>();
        double subtotalPuro = 0.0;
        int opcion = 0;

        do {
            System.out.println("\n--- MENU CLIENTE ---");
            System.out.println("1. Mostrar Catalogo y Añadir Plato");
            System.out.println("2. Aplicar cupon de descuento");
            System.out.println("3. Elegir tipo de envio");
            System.out.println("4. Finalizar Compra (Guardar Pedido)");
            System.out.println("5. Salir");
            System.out.print("Opcion: ");
            
            try { opcion = Integer.parseInt(scanner.nextLine()); } catch (Exception e) { opcion = 0; }

            switch (opcion) {
                case 1:
                    List<Producto> catalogo = productoDAO.obtenerCatalogo();
                    for (Producto p : catalogo) {
                        System.out.println("[" + p.getIdProducto() + "] " + p.getNombre() + " - S/ " + p.getPrecio());
                    }
                    System.out.print("\nID del plato: ");
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
                            System.out.println("-> Añadido: " + cant + "x " + p.getNombre());
                            break;
                        }
                    }
                    pedidoActual = new PedidoBase(subtotalPuro);
                    break;
                    
                case 2:
                    if (pedidoActual == null) break;
                    System.out.println("Descuentos: [1] Cumpleaños [2] Estudiante [3] Viernes");
                    String desc = scanner.nextLine();
                    if (desc.equals("1")) pedidoActual = new DescuentoCumpleaños(pedidoActual);
                    else if (desc.equals("2")) pedidoActual = new DescuentoEstudiante(pedidoActual);
                    else if (desc.equals("3")) pedidoActual = new DescuentoViernes(pedidoActual);
                    System.out.println("-> Descuento aplicado.");
                    break;

                case 3:
                    System.out.println("Envios: [EXPRESS] [PROGRAMADO] [RECOJO]");
                    String tipoEnvio = scanner.nextLine();
                    try {
                        entregaActual = EntregaFactory.crearEntrega(tipoEnvio);
                        entregaActual.tipoEntrega(); 
                    } catch (Exception e) { System.out.println("Error: " + e.getMessage()); }
                    break;

                case 4:
                    if (pedidoActual == null || carritoBD.isEmpty()) {
                        System.out.println("El carrito esta vacio.");
                        break;
                    }
                    double costoEnvio = (entregaActual != null) ? entregaActual.getCosto() : 0.0;
                    double totalFinal = pedidoActual.getTotal() + costoEnvio;
                    
                    System.out.println("\n====== TICKET ======");
                    System.out.println("Subtotal con descuentos: S/ " + pedidoActual.getTotal());
                    System.out.println("Costo de envio: S/ " + costoEnvio);
                    System.out.println("TOTAL FINAL: S/ " + totalFinal);
                    System.out.print("\n¿Confirmar y pagar? (S/N): ");
                    
                    if (scanner.nextLine().equalsIgnoreCase("S")) {
                        Pedido nuevoPedido = new Pedido();
                        nuevoPedido.setIdCliente(cliente.getId());
                        nuevoPedido.setEstado("PENDIENTE");
                        nuevoPedido.setCostoEnvio(costoEnvio);
                        nuevoPedido.setTotal(totalFinal);
                        nuevoPedido.setDetalles(carritoBD);
                        
                        if (pedidoDAO.registrarPedido(nuevoPedido)) {
                            System.out.println("-> COMPRA EXITOSA.");
                            carritoBD.clear();
                            subtotalPuro = 0.0;
                            pedidoActual = null;
                        }
                    }
                    break;
            }
        } while (opcion != 5);
    }
}