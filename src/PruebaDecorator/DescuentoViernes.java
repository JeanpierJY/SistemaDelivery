
package PruebaDecorator;

public class DescuentoViernes extends DescuentoDecorator {

    public DescuentoViernes(Compra compra) {
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