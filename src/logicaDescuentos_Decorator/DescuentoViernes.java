
package logicaDescuentos_Decorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class DescuentoViernes extends DescuentoDecorator {

    public DescuentoViernes(CompraBase compra) {
        super(compra);
    }

    @Override
    public String getDescripcion() {
        return compra.getDescripcion()
                + " + descuento viernes";
    }

    @Override
    public double getTotal() {
        return compra.getTotal() * 0.90;
    }
}