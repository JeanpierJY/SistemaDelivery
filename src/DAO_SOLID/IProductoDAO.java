/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_SOLID;

import java.util.List;
import modelos.Producto;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public interface IProductoDAO {
    List<Producto> obtenerCatalogo();
    boolean registrarProducto(Producto producto);
    boolean actualizarProducto(Producto producto);
    boolean eliminarProducto(int idProducto);
}
