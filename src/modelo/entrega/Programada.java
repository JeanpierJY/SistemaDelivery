package modelo.entrega;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class Programada implements ITipoEntrega {

    @Override
    public void programarEntrega() {
        System.out.println("Entrega programada");
    }

    @Override
    public double getCosto() {
        return 5;
    }
}