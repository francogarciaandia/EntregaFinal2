/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean.detalle;

import aplicacion.dao.imp.DetalleDAOImp;
import aplicacion.modelo.dominio.Detalle;
import javax.faces.bean.ManagedBean;
import aplicacion.dao.interfaz.DetalleDAO;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@SessionScoped
public class DetalleBean {
    private DetalleDAO detalleDao;
    private Detalle unDetalle;

    /**
     * Creates a new instance of DetalleBean
     */
    public DetalleBean() {
        detalleDao=new DetalleDAOImp();
        unDetalle=new Detalle();
    }
    public void agregarDetalle(Detalle unDetalle){
        detalleDao.agregar(unDetalle);
    }
    public void eliminarDetalle(Detalle unDetalle){
        detalleDao.eliminar(unDetalle);
    }
    public void modificarDetalle(Detalle unDetalle){
        detalleDao.modificar(unDetalle);
    }
    public Detalle obtenerDetalle(int iddetalle){
        return detalleDao.obtenerCategoria(iddetalle);
    }

    /**
     * @return the detalleDao
     */
    public DetalleDAO getDetalleDao() {
        return detalleDao;
    }

    /**
     * @param detalleDao the detalleDao to set
     */
    public void setDetalleDao(DetalleDAO detalleDao) {
        this.detalleDao = detalleDao;
    }

    /**
     * @return the unDetalle
     */
    public Detalle getUnDetalle() {
        return unDetalle;
    }

    /**
     * @param unDetalle the unDetalle to set
     */
    public void setUnDetalle(Detalle unDetalle) {
        this.unDetalle = unDetalle;
    }
    
}
