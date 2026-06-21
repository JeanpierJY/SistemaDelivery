/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logicaSeguimiento_Observer;

import java.util.ArrayList;
import java.util.List;
import modelos.Pedido;

public class SujetoPedido {
    // Lista de modulos que esperan ser notificados
    private List<IObservador> observadores = new ArrayList<>();

    public void agregarObservador(IObservador observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(IObservador observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores(Pedido pedido) {
        for (IObservador obs : observadores) {
            obs.actualizar(pedido);
        }
    }
}
