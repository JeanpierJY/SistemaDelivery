package modelos;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import estadoPedido_State.EstadoPedido;
import estadoPedido_State.EstadoEnProceso;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class Pedido {
    private int idPedido;
    private int idCliente;
    private Integer idRepartidor;
    private String estado; 
    private EstadoPedido estadoLogico; 
   

    private double costoEnvio;
    private double total;
    private Timestamp fechaRegistro;
    
    private List<DetallePedido> detalles = new ArrayList<>();

    public Pedido() {
        this.estadoLogico = new EstadoEnProceso();
        this.estado = "PENDIENTE";
    }

    public void avanzarEstado() {
        if (this.estadoLogico != null) {
            this.estadoLogico.siguienteEstado(this);
        }
    }

    public void mostrarEstadoActual() {
        if (this.estadoLogico != null) {
            this.estadoLogico.mostrarEstado();
        }
    }

    public void setEstadoLogico(EstadoPedido nuevoEstadoLogico, String nuevoTextoEstadoBD) {
        this.estadoLogico = nuevoEstadoLogico;
        this.estado = nuevoTextoEstadoBD;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(Integer idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EstadoPedido getEstadoLogico() {
        return estadoLogico;
    }

    public void setEstadoLogico(EstadoPedido estadoLogico) {
        this.estadoLogico = estadoLogico;
    }

    public double getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(double costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Timestamp getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Timestamp fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
 
}