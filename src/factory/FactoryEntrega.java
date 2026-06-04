package factory;

import modelo.entrega.Express;
import modelo.entrega.ITipoEntrega;
import modelo.entrega.Programada;
import modelo.entrega.RecojoTienda;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/

public class FactoryEntrega {

    public static ITipoEntrega crearEntrega(String tipo) {

        switch(tipo.toUpperCase()) {

            case "EXPRESS":
                return new Express();

            case "PROGRAMADA":
                return new Programada();

            case "RECOJO":
                return new RecojoTienda();

            default:
                throw new IllegalArgumentException("Tipo inválido");
        }
    }
}