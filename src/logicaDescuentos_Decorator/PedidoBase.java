/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaDescuentos_Decorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class PedidoBase implements CompraBase {
    private double totalSinDescuento;

    public PedidoBase(double totalSinDescuento) {
        this.totalSinDescuento = totalSinDescuento;
    }

    @Override
    public String getDescripcion() {
        return "Pedido basico";
    }

    @Override
    public double getTotal() {
        return totalSinDescuento;
    }
}
