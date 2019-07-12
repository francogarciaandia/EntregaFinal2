/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.interfaz;

import aplicacion.modelo.dominio.Categoria;
import java.util.List;

/**
 *
 * @author Flia. Vilca
 */
public interface CategoriaDAO {
    Categoria obtenerCategoria(String nombreCategoria);
    void agregar(Categoria unaCategoria);
    void eliminar(Categoria unaCategoria);
    void modificar(Categoria unaCategoria);
    List<Categoria> obtenerLista();
}
