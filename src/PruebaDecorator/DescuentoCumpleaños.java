package PruebaDecorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class DescuentoCumpleaños extends DescuentoDecorator {

    public DescuentoCumpleaños(Compra compra) {
        super(compra);
    }

    @Override
    public String getDescripcion() {
        return compra.getDescripcion() + " + descuento cumpleaños";
    }

    @Override
    public double getTotal() {
        return compra.getTotal() * 0.80;
    }
}