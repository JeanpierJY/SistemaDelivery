package logicaDescuentos_Decorator;


/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public abstract class DescuentoDecorator implements CompraBase {

    protected CompraBase compra;

    public DescuentoDecorator(CompraBase compra) {
        this.compra = compra;
    }
}