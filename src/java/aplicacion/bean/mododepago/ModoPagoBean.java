/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean.mododepago;

import aplicacion.dao.imp.ModoPagoDAOImp;
import aplicacion.modelo.dominio.ModoPago;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import aplicacion.dao.interfaz.ModoPagoDAO;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@RequestScoped
public class ModoPagoBean {
    private ModoPagoDAO modopagoDAO;
    

    /**
     * Creates a new instance of ModoPagoBean
     */
    public ModoPagoBean() {
        modopagoDAO=new ModoPagoDAOImp();
        
    }
    public void crearModoPago(ModoPago unModoPago){
        getModopagoDAO().agregar(unModoPago);
    }
    public void eliminarModoPago(ModoPago unModoPago){
        getModopagoDAO().eliminar(unModoPago);
    }
    public void modificarModoPago(ModoPago unModoPago){
        getModopagoDAO().modificar(unModoPago);
    }
    public ModoPago obtenerModoPago(int idModoPago){
        return getModopagoDAO().obtenerModoPago(idModoPago);
    }

    public ModoPagoDAO getModopagoDAO() {
        return modopagoDAO;
    }

    public void setModopagoDAO(ModoPagoDAO modopagoDAO) {
        this.modopagoDAO = modopagoDAO;
    }
    
}
