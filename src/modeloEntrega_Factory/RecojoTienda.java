/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloEntrega_Factory;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class RecojoTienda implements TipoEntrega {

    @Override
    public void tipoEntrega() {
        System.out.println("Recojo en tienda");
    }

    @Override
    public double getCosto() {
        return 0;
    }
    
}
