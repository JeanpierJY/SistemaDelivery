package Pruebafactory;

import Pruebamodelo.entrega.Express;
import Pruebamodelo.entrega.ITipoEntrega;
import Pruebamodelo.entrega.Programada;
import Pruebamodelo.entrega.RecojoTienda;

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