/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean.categoria;

import aplicacion.dao.imp.CategoriaDAOImp;
import aplicacion.modelo.dominio.Categoria;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import aplicacion.dao.interfaz.CategoriaDAO;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@SessionScoped
public class CategoriaBean  {
    private CategoriaDAO categoriaDAO;
    

    /**
     * Creates a new instance of CategoriaBean
     */
    public CategoriaBean() {
        categoriaDAO=new CategoriaDAOImp();
      
    }
    public void agregarCategoria(Categoria unaCategoria){
        getCategoriaDAO().agregar(unaCategoria);
    }
    public void eliminarCategoria(Categoria unaCategoria){
        getCategoriaDAO().eliminar(unaCategoria);
    }
    public void modificarCategoria(Categoria unaCategoria){
        getCategoriaDAO().modificar(unaCategoria);
    }
    public Categoria obtenerCategoria(String nombreCat){
        return getCategoriaDAO().obtenerCategoria(nombreCat);
    }
    public List<Categoria> obtenerLista(){
        return getCategoriaDAO().obtenerLista();
    }
    public CategoriaDAO getCategoriaDAO() {
        return categoriaDAO;
    }

   
    public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

}
