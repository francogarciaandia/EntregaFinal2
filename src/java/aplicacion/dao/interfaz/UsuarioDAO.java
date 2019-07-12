/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.interfaz;

import aplicacion.modelo.dominio.Usuario;
import java.util.List;


/**
 *
 * @author alvar
 */
public interface UsuarioDAO {
    public Usuario validarUsuario(String nombreUsuario, String password);
    public Usuario obtenerUsuario(String nombreUsuario);
    public void modificarUsuario(Usuario unUsuario);
    public void crearUsuario(Usuario unUsuario);
    public void eliminarUsuario(Usuario unUsuario);
    public List<Usuario> listaUsuario();
}
