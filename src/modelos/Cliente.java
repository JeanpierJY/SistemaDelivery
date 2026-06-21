/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.util.Date;

/**
 *
 * @author Jean
 */
public class Cliente extends Persona {
    private Date fechaCumpleaños;
    private String direccion;
    
    public Cliente() {
    }

    public Date getFechaCumpleaños() {
        return fechaCumpleaños;
    }

    public void setFechaCumpleaños(Date fechaCumpleaños) {
        this.fechaCumpleaños = fechaCumpleaños;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
}
