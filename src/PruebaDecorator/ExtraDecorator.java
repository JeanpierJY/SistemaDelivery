package PruebaDecorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

//Decorator abstracto: hereda del mismo tipo
public abstract class ExtraDecorator implements Producto {

    protected Producto producto;

    public ExtraDecorator(Producto producto) {
        this.producto = producto;
    }
}