/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integracionAPIs_Adapter;

/**
 *
 * @author Jean
 */
public interface IValidacionVehicular {
    boolean validarLicenciaMTC(String dni);
    boolean validarSoatActivo(String placa);
}
