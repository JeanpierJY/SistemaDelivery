/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO_SOLID;

import java.util.List;
import modelos.Persona;

/*
SANCHEZ MAMANI, JEANPIERRE
ALARCON BARDALES, GIANELLA SOPHIA
*/
public interface IUsuarioDAO {
    Persona loginUsuario(String email, String password);
    boolean registrarUsuario(Persona nuevoUsuario, String password);
    List<Persona> obtenerUsuarios();
    boolean actualizarUsuario(Persona usuario);
    boolean eliminarUsuario(int idUsuario);
}