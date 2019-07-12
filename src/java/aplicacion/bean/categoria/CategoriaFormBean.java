package aplicacion.bean.categoria;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import aplicacion.bean.categoria.CategoriaBean;
import aplicacion.modelo.dominio.Categoria;
import java.io.Serializable;
import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@SessionScoped
public class CategoriaFormBean implements Serializable{
    @ManagedProperty (value = "#{categoriaBean}")
    private CategoriaBean categoriaBean;
    private Categoria unaCategoria;
    private List<Categoria> obtenerCategoriass;
    /**
     * Creates a new instance of CategoriaFormBean
     */
    public CategoriaFormBean() {
       
        unaCategoria=new Categoria();
    }
    @PostConstruct
    public void init()
    {
         categoriaBean=new CategoriaBean();
         obtenerCategoriass=categoriaBean.obtenerLista();
    }
     public void onRowEdit(RowEditEvent event)
  {   Categoria c=(Categoria) event.getObject();
         modificarCategoria(c); 
                 }
   public void onRowEditCancel(RowEditEvent event)
  {   FacesMessage facesmessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"C cancelo la edicion", "Cambios no guardados");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
  }
    public void agregarCategoria(){
        Random r= new Random(System.currentTimeMillis());
         int codigo = (int) r.nextInt(200);
        getUnaCategoria().setIdcategoria((int) codigo);
         try {
               getCategoriaBean().agregarCategoria(getUnaCategoria());
               obtenerCategoriasss();
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Categoria agreagada correctamente","bien hecho");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo agregar Categoria");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void eliminarCategoria(Categoria y){
         try {
               getCategoriaBean().eliminarCategoria(y);
               obtenerCategoriasss();
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Categoria eliminada correctamente","Producto");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminar Categoria");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void modificarCategoria(Categoria r){
         try {
               getCategoriaBean().modificarCategoria(r);
               obtenerCategoriasss();
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Categoria modificada correctamente","Categoria");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar Categoria");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }

    public void obtenerCategoriasss()
    {
        setObtenerCategoriass(getCategoriaBean().obtenerLista());
    }
    public CategoriaBean getCategoriaBean() {
        return categoriaBean;
    }

    public void setCategoriaBean(CategoriaBean categoriaBean) {
        this.categoriaBean = categoriaBean;
    }

    public Categoria getUnaCategoria() {
        return unaCategoria;
    }

    public void setUnaCategoria(Categoria unaCategoria) {
        this.unaCategoria = unaCategoria;
    }

    public List<Categoria> getObtenerCategoriass() {
        return obtenerCategoriass;
    }

    public void setObtenerCategoriass(List<Categoria> obtenerCategoriass) {
        this.obtenerCategoriass = obtenerCategoriass;
    }

   
}
