/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.beans.productos;

import aplicacion.modelo.dominio.Categoria;
import aplicacion.modelo.dominio.Producto;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@SessionScoped
public class ProductoFormBean implements Serializable{
    @ManagedProperty (value = "#{productoBean}")
    private ProductoBean productoBean;
   
    private int codProducto;
    private Producto unProducto;
    private List<Producto> productos;
    private List<Categoria> categorias;
    private transient UploadedFile archivo1=null;
    private Producto productoSeleccionado;
    private Producto otroProducto;

    /**
     * Creates a new instance of ProductoFormBean
     */
    public ProductoFormBean() {
        productoBean=new ProductoBean();
        unProducto=new Producto();
        categorias=new ArrayList();
        productos=new ArrayList();
        productoSeleccionado=new Producto();
        otroProducto=new Producto();
    }
    @PostConstruct
    public void init(){
        obtenerLista();
        listaCategoria();
    }
    
    public void agregarProducto(){
        getUnProducto().setCodProducto((int) (Math.random()*1000000));
        if(getArchivo1() != null){
            byte[] contents = getArchivo1().getContents();
            getUnProducto().setFoto(contents);
        }
        else{
            getUnProducto().setFoto(null);
        }
           try {
               getProductoBean().crearProducto(getUnProducto());
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto agreagado correctamente","Producto");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo agregar Producto");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
           setUnProducto(new Producto());
           obtenerLista();
    }
    public void eliminarProducto(){
         try {
               getProductoBean().eliminarProducto(getProductoSeleccionado());
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto eliminado correctamente","Producto");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminar Producto");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void mostrarProducto(Producto ggproducto)
    {//System.out.println(ggproducto.getCodProducto());
       otroProducto=ggproducto;
    }
    public void modificarProducto(){
         try {
               getProductoBean().modificarProducto(getProductoSeleccionado());
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Producto modificado correctamente","Producto");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar Producto");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void obtenerLista(){
        setProductos(getProductoBean().obtenerLista());
    }
    public void listaCategoria(){
        setCategorias(getProductoBean().obtenerCategoriases());
    }
    public StreamedContent getFotoproducto() throws IOException{
     FacesContext context=FacesContext.getCurrentInstance();
     if(context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE){
      return new DefaultStreamedContent();
     }
     else{
        String codigo=context.getExternalContext().getRequestParameterMap().get("codProd");
        Producto producto=getProductoBean().obtenerProducto(Integer.parseInt(codigo));
        if (producto.getFoto()==null){
         return null;
        }
        else{
         return new DefaultStreamedContent(new ByteArrayInputStream(producto.getFoto()));        
        }
      }
    }

    /**
     * @return the productoBean
     */
    public ProductoBean getProductoBean() {
        return productoBean;
    }

    /**
     * @param productoBean the productoBean to set
     */
    public void setProductoBean(ProductoBean productoBean) {
        this.productoBean = productoBean;
    }

    /**
     * @return the codProducto
     */
    public int getCodProducto() {
        return codProducto;
    }

    /**
     * @param codProducto the codProducto to set
     */
    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    /**
     * @return the unProducto
     */
    public Producto getUnProducto() {
        return unProducto;
    }

    /**
     * @param unProducto the unProducto to set
     */
    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
    }


    /**
     * @return the productos
     */
    public List<Producto> getProductos() {
        return productos;
    }

    /**
     * @param productos the productos to set
     */
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    /**
     * @return the categorias
     */
    public List<Categoria> getCategorias() {
        return categorias;
    }

    /**
     * @param categorias the categorias to set
     */
    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
    /**
     * @return the productoSeleccionado
     */
    public Producto getProductoSeleccionado() {
        return productoSeleccionado;
    }

    /**
     * @param productoSeleccionado the productoSeleccionado to set
     */
    public void setProductoSeleccionado(Producto productoSeleccionado) {
        this.productoSeleccionado = productoSeleccionado;
    }

    public Producto getOtroProducto() {
        return otroProducto;
    }

    public void setOtroProducto(Producto otroProducto) {
        this.otroProducto = otroProducto;
    }

    public UploadedFile getArchivo1() {
        return archivo1;
    }

    public void setArchivo1(UploadedFile archivo1) {
        this.archivo1 = archivo1;
    }
  
}
