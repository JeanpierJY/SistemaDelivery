package Decorator;
/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

// Componente concreto: pasta base
public class PastaAlfredo implements Producto {

    @Override
    public String getDescripcion() {
        return "Pasta Alfredo";
    }

    @Override
    public double getPrecio() {
        return 20;
    }
}