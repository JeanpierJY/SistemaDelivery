/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores_GRASP;

import DAO_SOLID.IUsuarioDAO;
import DAO_SOLID.UsuarioDAOPostgres;
import integracionAPIs_Adapter.ApiPeruAdapter;
import modelos.Repartidor;
import config_Singleton.LoggerSistema;

public class ControladorUsuario {
    
    private IUsuarioDAO usuarioDAO = new UsuarioDAOPostgres();
    private ApiPeruAdapter validadorAPI = new ApiPeruAdapter();

    public boolean registrarNuevoRepartidor(Repartidor nuevo, String password) {
        LoggerSistema.getInstancia().registerLog("Validando datos en RENIEC y MTC...");
        
        String datosDni = validadorAPI.consultarNombrePorDNI(nuevo.getDni());
        if (datosDni == null) {
            LoggerSistema.getInstancia().error("DNI no existe.");
            return false; 
        }

        boolean licenciaValida = validadorAPI.validarLicenciaMTC(nuevo.getDni());
        if (!licenciaValida) {
            LoggerSistema.getInstancia().error("Sin licencia.");
            return false; 
        }

        boolean soatValido = validadorAPI.validarSoatActivo(nuevo.getPlacaMoto());
        if (!soatValido) {
            LoggerSistema.getInstancia().error("Sin SOAT vigente.");
            return false; 
        }
        
        return usuarioDAO.registrarUsuario(nuevo, password);
    }
}