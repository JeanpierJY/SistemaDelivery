/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modeloEntrega_Factory;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public class EntregaFactory {
    public static TipoEntrega crearEntrega(String tipo){
        
        switch(tipo.toUpperCase()){
            case "EXPRESS":
                return new EntregaExpress();
            case "PROGRAMADO":
                return new EntregaProgramada();
            case "RECOJO EN TIENDA":
                return new RecojoTienda();
                
            default:
                throw new IllegalArgumentException("Tipo invalido");
        }
    }
}
