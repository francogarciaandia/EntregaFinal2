/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.interfaz;

import aplicacion.modelo.dominio.Categoria;
import aplicacion.modelo.dominio.Producto;
import java.util.List;

/**
 *
 * @author Flia. Vilca
 */
public interface ProductoDAO {
    Producto obtenerProducto(int codProducto);
    void agregar(Producto unProducto);
    void eliminar(Producto unProducto);
    void modificar(Producto unProducto); 
    List<Producto> obtenerLista();
    List<Categoria> obtenerCategorias();
}
