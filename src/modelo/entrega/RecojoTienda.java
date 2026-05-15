package modelo.entrega;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class RecojoTienda implements ITipoEntrega {
    
    @Override
    public void programarEntrega() {
        System.out.println("Recojo en tienda");
    }

    @Override
    public double getCosto() {
        return 0;
    }
}
