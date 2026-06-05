/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloEntrega_Factory;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class EntregaProgramada implements TipoEntrega {

    @Override
    public void tipoEntrega(){
        System.out.println("Entrega programada");
    }

    @Override
    public double getCosto() {
        return 5;
    }
    
}
