package aplicacion.bean.detalle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import aplicacion.modelo.dominio.Detalle;
import aplicacion.modelo.dominio.Factura;
import aplicacion.modelo.dominio.ModoPago;
import aplicacion.modelo.dominio.Producto;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@SessionScoped
public class DetalleFormBean implements Serializable{
    @ManagedProperty(value = "#{detalleBean}")
    private DetalleBean detalleBean;
    private Factura unaFactura;
    private Producto unProducto;
    private Detalle unDetalle;
    private ModoPago modoPago;
    private List<Detalle> listadodetallealternativo;
    private  List <Detalle> otroDetalles;
    private int num;
    private ArrayList<String> decripciones;
 
    /**
     * Creates a new instance of DetalleFormBean
     */
    public DetalleFormBean() {
        detalleBean =new DetalleBean();
        unaFactura=new Factura();
        unProducto=new Producto();
        unDetalle=new Detalle();
        modoPago=new ModoPago();
        listadodetallealternativo=new ArrayList();
        otroDetalles =new ArrayList();
  
    }
    @PostConstruct
    public void init()
    {decripciones=new ArrayList();
        decripciones.add("efectivo");
        decripciones.add("tarjeta de credito");
        decripciones.add("Tarjeta de debito");
        decripciones.add("paypal");
        modoPago.setCuotas(0);
    }
   
    public void agregarDetalle(Detalle d){
      
       
         try {
            getDetalleBean().agregarDetalle(d);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"hubo un inconveniente al intentar agregar","no se pudo agregar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public String reiniciarLista()
    {
        setListadodetallealternativo(new ArrayList());
        return "paginaproductos?faces-redirect=true";
    }
    public String reiniciarLista1()
    {
        setListadodetallealternativo(new ArrayList());
        setOtroDetalles(new ArrayList());
        return "login?faces-redirect=true";
    }
    public List<Detalle> obtenerDetalle()
    {
        
        return listadodetallealternativo;
    }
    public void agregardetalle(Producto r)
    {
        Detalle ggDetalle=new Detalle();
        ggDetalle.setProductos(r);
        ggDetalle.setCantidad(0);
        ggDetalle.setIddetalle((int) (Math.random()*1000000));
        getListadodetallealternativo().add(ggDetalle);
        System.out.println(ggDetalle.getIddetalle());
        FacesMessage facesMessage =new FacesMessage(FacesMessage.SEVERITY_INFO, "Se agrego un nuevo producto a su lista de compra", " siga seleccionando sus productos :) ");
       FacesContext context= FacesContext.getCurrentInstance();
       context.addMessage(null, facesMessage);
    }
    public void volverListado()
    {
        otroDetalles=new ArrayList();
       for (int i=0;i< listadodetallealternativo.size();i++)
        {
            if(listadodetallealternativo.get(i).isEstado()!=false )
            {
                otroDetalles.add(listadodetallealternativo.get(i));
            }
            
        }
       
    }
    public double sumatotal()
    {
        double y=0;
        for (int i=0; i<otroDetalles.size();i++)
        {
            y=y+ Double.parseDouble(otroDetalles.get(i).getPrecioProdCant());
        }
        return y;
    }
 public boolean validarutilidad( ModoPago u)
 {
     return "efectivo".equals(u.getDescripcion());
 }
    public String  generarFactura()
    { String y=null;
       Date fDate = new Date();
      modoPago.setInteres((double)(modoPago.getCuotas())+3.0);
      // unaFactura.setFecha(Date.from(Instant.now()));
        unaFactura.setUsuarios((Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariovalidado"));
         modoPago.setIdmodoPago((int) (Math.random()*1000000));
            unaFactura.setModopago(modoPago);
            unaFactura.setFecha(fDate);
            unaFactura.setNumFactura((int) (Math.random()*1000000));
        
         for (int i=0;i< listadodetallealternativo.size();i++)
        {
           
          listadodetallealternativo.get(i).setFactura(unaFactura);
        
        }
            for (int i=0;i< listadodetallealternativo.size();i++)
        {
           
         agregarDetalle(listadodetallealternativo.get(i));
        
        }
            return y="ticket?faces-redirect=true";
    }
    public String sumarPrecios(Detalle e)
    {double t=0;String a;
        for(int i=0;i<otroDetalles.size();i++)
        {
            t=t+(otroDetalles.get(i).getProductos().getPrecio()*e.getCantidad());
        }
         a=Double.toString(t);
        return a ;
    }
    public void eliminarDetalle(){
         try {
             getDetalleBean().eliminarDetalle(unDetalle);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle eliminado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }
    public void modificarDetalle(){
         try {
            getDetalleBean().modificarDetalle(unDetalle);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Detalle modificado correctamente","Detalle");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar Detalle");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
    }

    
    /**
     * @return the detalleBean
     */
    public DetalleBean getDetalleBean() {
        return detalleBean;
    }

    /**
     * @param detalleBean the detalleBean to set
     */
    public void setDetalleBean(DetalleBean detalleBean) {
        this.detalleBean = detalleBean;
    }

    /**
     * @return the unaFactura
     */
    public Factura getUnaFactura() {
        return unaFactura;
    }

    /**
     * @param unaFactura the unaFactura to set
     */
    public void setUnaFactura(Factura unaFactura) {
        this.unaFactura = unaFactura;
    }

    /**
     * @return the unProducto
     */
    public Producto getUnProducto() {
        return unProducto;
    }
 public double  calculo (Detalle unaDetalle)
 {
     if(unaDetalle.getCantidad()==null)
     {
         unaDetalle.setCantidad(1);
     }
     
     double y=((double) unaDetalle.getCantidad())* unaDetalle.getProductos().getPrecio();
   String  i= Double.toString(y);
   unaDetalle.setPrecioProdCant(i);
    return y;
 }
    /**
     * @param unProducto the unProducto to set
     */
    public void setUnProducto(Producto unProducto) {
        this.unProducto = unProducto;
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

    /**
     * @return the modoPago
     */
    public ModoPago getModoPago() {
        return modoPago;
    }

    /**
     * @param modoPago the modoPago to set
     */
    public void setModoPago(ModoPago modoPago) {
        this.modoPago = modoPago;
    }

    

    public List<Detalle> getListadodetallealternativo() {
        return listadodetallealternativo;
    }

    public void setListadodetallealternativo(List<Detalle> listadodetallealternativo) {
        this.listadodetallealternativo = listadodetallealternativo;
    }

    public int getNum() {
        return num;
    }

    public ArrayList<String> getDecripciones() {
        return decripciones;
    }

    public void setDecripciones(ArrayList<String> decripciones) {
        this.decripciones = decripciones;
    }

    public List<Detalle> getOtroDetalles() {
        return otroDetalles;
    }

    public void setOtroDetalles(List<Detalle> otroDetalles) {
        this.otroDetalles = otroDetalles;
    }
}
