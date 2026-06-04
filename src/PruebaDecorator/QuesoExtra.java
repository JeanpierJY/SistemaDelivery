/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PruebaDecorator;

/**
 *
 * @author Jean
 */
// Decorador concreto: Queso Extra
public class QuesoExtra extends PastaDecorator {

    public QuesoExtra(PlatoPasta plato) {
        super(plato);
    }

    @Override
    public String getDescripcion() {
        // Ejecuta el metodo original y le concatena su propia descripcion
        return super.getDescripcion() + ", con queso extra";
    }

    @Override
    public double getCosto() {
        // Ejecuta el metodo original y le suma su propio costo
        return super.getCosto() + 2.50;
    }
}
