/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp;

import aplicacion.dao.interfaz.UsuarioDAO;
import aplicacion.modelo.dominio.Cliente;
import aplicacion.modelo.dominio.Usuario;

/**
 *
 * @author santiago
 */
public class UsuarioAdministradorRoot {
    public static void main(String[] args) {
         UsuarioDAO usuarioDAO = new UsuarioDAOImp();
         Usuario unUsuario= new Usuario();
         Cliente unCliente= new Cliente();
         unUsuario.setApellidos("churquina");
         unUsuario.setCodigo(0);
         unUsuario.setEstado(true);
         unUsuario.setNombreUsuario("admin");
         unUsuario.setPassword("admin");
         unUsuario.setTipoUsuario("administrador");
         unUsuario.setNombres("Santiago Lucas");
         unCliente.setDni(43211168);
         unCliente.setDomicilio("alto comedero");
         unCliente.setMail("santiagochurquina@95gmail.com");
         unCliente.setProvincia("jujuy");
         unCliente.setPais("argentina");
         unUsuario.setClientes(unCliente);
         usuarioDAO.crearUsuario(unUsuario);
    }
}
