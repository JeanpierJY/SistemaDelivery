
package PruebaDecorator;

// Decorador concreto: Pollo Extra
public class PolloExtra extends ExtraDecorator {

    public PolloExtra(Producto producto) {
        super(producto);
    }

    @Override
    public String getDescripcion() {
        return producto.getDescripcion()
                + " + pollo";
    }

    @Override
    public double getPrecio() {
        return producto.getPrecio() + 8;
    }
}
