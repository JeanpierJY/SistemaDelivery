package PruebaDecorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public abstract class DescuentoDecorator implements Compra {

    protected Compra compra;

    public DescuentoDecorator(Compra compra) {
        this.compra = compra;
    }
}