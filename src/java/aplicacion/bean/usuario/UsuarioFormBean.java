/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.bean.usuario;

import aplicacion.modelo.dominio.Cliente;
import aplicacion.modelo.dominio.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author TOSHIBA
 */
@ManagedBean
@RequestScoped
public class UsuarioFormBean implements Serializable{
    @ManagedProperty(value = "#{usuarioBean}")
    private UsuarioBean usuarioBean; 
    private Cliente unCliente;
    private Usuario unUsuario;
    private String nombre;
    private String pass;
    private String nombreUsuario;
    private List<Usuario> listUsuarios;
    private Usuario validadoUsuario;
    private boolean admin;
    private List<String> paises;
    private List<String> provincias;
    private List<String> privilegio;
    private  boolean  consulto;
    private boolean consultop;
    private Cliente validadoCliente;
   
            
    /**
     * Creates a new instance of UsuarioFormBean
     */
    public UsuarioFormBean() {
        unCliente=new Cliente();
        unUsuario=new Usuario();
        listUsuarios=new ArrayList();
       privilegio=new ArrayList(); 
       paises=new ArrayList();
        provincias=new ArrayList();
       
    }
    @PostConstruct
    public void init(){
         usuarioBean= new UsuarioBean();
       listUsuarios=usuarioBean.listaUsuario();
       privilegio.add("cliente");
       privilegio.add("adminitrador");
        paises.add("Bolivia");
       paises.add("Uruguay");
       paises.add("Brasil");
       paises.add("Chile");
       paises.add("Venezuela");
       provincias.add("Alto comedero");
       provincias.add("Escuela de Minas");
       provincias.add("Palpala");
       provincias.add("Tucuman");
       provincias.add("Salta");
       provincias.add("Formosa");
       provincias.add("misiones");
     
    }
        public void onRowEdit(RowEditEvent event)
  {   Usuario usuario= (Usuario) event.getObject();
         modificarUsuario(usuario);
  }
   public void onRowEditCancel(RowEditEvent event)
  {   FacesMessage facesmessage= new FacesMessage(FacesMessage.SEVERITY_INFO,"C cancelo la edicion", "Cambios no guardados");
            FacesContext.getCurrentInstance().addMessage(null, facesmessage);
  }      
    public void crearUsuario(){
           getUnUsuario().setEstado(true);
           getUnUsuario().setTipoUsuario("cliente");
           getUnUsuario().setCodigo((int) (Math.random()*1000000));
           getUnUsuario().setClientes(unCliente);
           try {
               usuarioBean.agregarUsuario(unUsuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario agreagado correctamente","Usuario" + unUsuario.getApellidos());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
        
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo crear usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                       
           }
           setUnUsuario(new Usuario());
        }
    
    public void crearUsuarioAdmioCliente(){
           getUnUsuario().setEstado(true);
          obtenerListado();
           //getUnUsuario().setTipoUsuario("cliente");
           getUnUsuario().setCodigo((int) (Math.random()*1000000));
           getUnUsuario().setClientes(unCliente);
           try {
               usuarioBean.agregarUsuario(unUsuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario agreagado correctamente","Usuario" + unUsuario.getApellidos());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
        
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo crear usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
                       
           }
           setUnUsuario(new Usuario());
        }
    
       public String validarUsuario() 
    {String pagina=null;
        setUnUsuario(getUsuarioBean().validarUsuario(getNombre(), getPass()));
        if(getUnUsuario()!=null)
        {
            if(getUnUsuario().getTipoUsuario().equalsIgnoreCase("Administrador"))
            {   
               
                FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
                fc.getExternalContext().getSessionMap().put("tipousuario", true);
               fc.getExternalContext().getSessionMap().put("clientevalidado", unUsuario.getClientes());
                pagina="paginainicio?faces-redirect=true";
               
            }
            else
            {
                 FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("tipousuario", false);
                 pagina="paginainicio?faces-redirect=true";
             
            }
        }
        else
        {
            FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_INFO, "Contraseña o nombre de usuario invalidos", "por favor vuelva a ingresarlas");
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        }
        return pagina;
    }
        public void modificarUsuario(Usuario ggusuario){
             try {
               usuarioBean.modificarUsuario(ggusuario);
               if(ggusuario.getCodigo()== getValidadoUsuario().getCodigo())
               {
                     FacesContext fc = FacesContext.getCurrentInstance();
                fc.getExternalContext().getSessionMap().put("usuariovalidado", unUsuario);
                fc.getExternalContext().getSessionMap().put("clientevalidado", validadoCliente);
               ggusuario.setClientes(validadoCliente);
               }
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario modificado correctamente", ggusuario.getNombreUsuario() );
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo modificar usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
            
        }
        public void eliminarUsuario(Usuario ty){
            if(getValidadoUsuario().getCodigo()==0)
            {
                 try {
                ty.setEstado(false);
               usuarioBean.eliminarUsuario(ty);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario eliminado correctamente","Usuario: " + ty.getNombreUsuario());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminado usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
            }
            else
            {
                
            }
            if(getValidadoUsuario().getTipoUsuario().equalsIgnoreCase("administrador") && ty.getTipoUsuario().equalsIgnoreCase("administrador"))
            {
                  FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"Error al intentar eliminar"," un usuario administrador no puede eliminar a otro. a exepcion de administrador root ");
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
            }
            else
            {
                 try {
                ty.setEstado(false);
               usuarioBean.eliminarUsuario(ty);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario eliminado correctamente","Usuario: " + ty.getNombreUsuario());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error Grave","no se pudo eliminado usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
            }
        }
        public void obtenerUsuario(){
             try {
               usuarioBean.obtenerUsuario(nombreUsuario);
               FacesMessage facesMesagge=new FacesMessage(FacesMessage.SEVERITY_INFO,"usuario encontrado","Usuario" + unUsuario.getApellidos());
               FacesContext.getCurrentInstance().addMessage(null, facesMesagge);
           }
           catch(Exception e){
               FacesMessage facesMessage=new FacesMessage(FacesMessage.SEVERITY_WARN,"Error","no se pudo encontrar usuario");
                       FacesContext.getCurrentInstance().addMessage(null, facesMessage);
           }
        }
        public List<Usuario> obtenerListado(){
           return getUsuarioBean().listaUsuario();
        }

    public List<String> getPrivilegio() {
        return privilegio;
    }

    public List<String> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<String> provincias) {
        this.provincias = provincias;
    }
    

    public void setPrivilegio(List<String> privilegio) {
        this.privilegio = privilegio;
    }

        
    /**
     * @return the usuarioBean
     */
    public UsuarioBean getUsuarioBean() {
        return usuarioBean;
    }

    /**
     * @param usuarioBean the usuarioBean to set
     */
    public void setUsuarioBean(UsuarioBean usuarioBean) {
        this.usuarioBean = usuarioBean;
    }

    /**
     * @return the unCliente
     */
    public Cliente getUnCliente() {
        return unCliente;
    }

    /**
     * @param unCliente the unCliente to set
     */
    public void setUnCliente(Cliente unCliente) {
        this.unCliente = unCliente;
    }

    /**
     * @return the unUsuario
     */
    public Usuario getUnUsuario() {
        return unUsuario;
    }

    /**
     * @param unUsuario the unUsuario to set
     */
    public void setUnUsuario(Usuario unUsuario) {
        this.unUsuario = unUsuario;
    }

    /**
     * @return the nombreUsuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * @param nombreUsuario the nombreUsuario to set
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * @return the listUsuarios
     */
    public List<Usuario> getListUsuarios() {
        return listUsuarios;
    }

    /**
     * @param listUsuarios the listUsuarios to set
     */
    public void setListUsuarios(List<Usuario> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getValidadoUsuario() {
        Usuario segundoUsuario=(Usuario)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariovalidado");
        validadoUsuario=segundoUsuario;
        return validadoUsuario;
    }

    public void setValidadoUsuario(Usuario validadoUsuario) {
        this.validadoUsuario = validadoUsuario;
    }

    public boolean isAdmin() {
            boolean t= (boolean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("tipousuario");
            admin=t;
            return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public List<String> getPaises() {
        return paises;
    }

    public void setPaises(List<String> paises) {
        this.paises = paises;
    }

    public Cliente getValidadoCliente() {
        Cliente c = (Cliente)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("clientevalidado");
        validadoCliente=c;
        return validadoCliente;
    }

    public void setValidadoCliente(Cliente validadoCliente) {
        this.validadoCliente = validadoCliente;
    }

   
  
}
