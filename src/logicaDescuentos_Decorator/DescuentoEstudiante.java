package logicaDescuentos_Decorator;


/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class DescuentoEstudiante extends DescuentoDecorator {

    public DescuentoEstudiante(CompraBase compra) {
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