/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.beans.productos;

import aplicacion.dao.imp.ProductoDAOImp;
import aplicacion.modelo.dominio.Producto;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import aplicacion.dao.interfaz.ProductoDAO;
import aplicacion.modelo.dominio.Categoria;
/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@SessionScoped
public class ProductoBean {
    private ProductoDAO productoDao;
    
    /**
     * Creates a new instance of ProductoBean
     */
    public ProductoBean() {
        productoDao=new ProductoDAOImp();      
    }
    public void crearProducto(Producto unProducto){
        getProductoDao().agregar(unProducto);
    }
    public void eliminarProducto(Producto unProducto){
        getProductoDao().eliminar(unProducto);
    }
    public void modificarProducto(Producto unProducto){
        getProductoDao().modificar(unProducto);
    }
    public Producto obtenerProducto(int codProducto){
    return getProductoDao().obtenerProducto(codProducto);
    }
    public List<Categoria> obtenerCategoriases()
    {
        return getProductoDao().obtenerCategorias();
    }
    public List<Producto> obtenerLista(){
     return productoDao.obtenerLista();
    }

    /**
     * @return the productoDao
     */
    public ProductoDAO getProductoDao() {
        return productoDao;
    }

    /**
     * @param productoDao the productoDao to set
     */
    public void setProductoDao(ProductoDAO productoDao) {
        this.productoDao = productoDao;
    }
}
