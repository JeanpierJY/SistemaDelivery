
package PruebaDecorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class PedidoBase implements Compra {

    private double total;

    public PedidoBase(double total) {
        this.total = total;
    }

    @Override
    public String getDescripcion() {
        return "Pedido de pastas";
    }

    @Override
    public double getTotal() {
        return total;
    }
}
