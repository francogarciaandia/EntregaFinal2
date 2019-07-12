/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.interfaz;

import aplicacion.modelo.dominio.Factura;

/**
 *
 * @author Flia. Vilca
 */
public interface FacturaDAO {
    Factura obtenerFactura(int numFactura);
    void agregar(Factura unaFactura);
    void eliminar(Factura unaFactura);
    void modificar(Factura unaFactura); 
}
