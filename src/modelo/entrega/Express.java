package modelo.entrega;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class Express implements ITipoEntrega {

    @Override
    public void programarEntrega() {
        System.out.println("Entrega express");
    }

    @Override
    public double getCosto() {
        return 10;
    }
}