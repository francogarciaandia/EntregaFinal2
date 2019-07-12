/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.interfaz;

import aplicacion.modelo.dominio.Detalle;

/**
 *
 * @author Flia. Vilca
 */
public interface DetalleDAO {
    Detalle obtenerCategoria(int iddetalle);
    void agregar(Detalle unDetalle);
    void eliminar(Detalle unDetalle);
    void modificar(Detalle unDetalle);
}
