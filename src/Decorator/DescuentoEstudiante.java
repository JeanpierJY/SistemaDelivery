package Decorator;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class DescuentoEstudiante extends DescuentoDecorator {

    public DescuentoEstudiante(Compra compra) {
        super(compra);
    }

    @Override
    public String getDescripcion() {
        return compra.getDescripcion()
                + " + descuento estudiante";
    }

    @Override
    public double getTotal() {
        return compra.getTotal() * 0.85;
    }
}