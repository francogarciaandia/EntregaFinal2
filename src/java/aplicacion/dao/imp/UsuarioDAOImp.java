/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacion.dao.imp;

import aplicacion.hibernate.configuracion.NewHibernateUtil;
import aplicacion.modelo.dominio.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import aplicacion.dao.interfaz.UsuarioDAO;

/**
 *
 * @author alvar
 */
public class UsuarioDAOImp implements UsuarioDAO {

    @Override
    public Usuario validarUsuario(String nombreUsuario, String password) {
        Usuario u =null;
        Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("nombreUsuario", nombreUsuario));
        criteria.add(Restrictions.like("password",password));
        criteria.add(Restrictions.eq("estado",true));
        if(!criteria.list().isEmpty())
            u=(Usuario)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return u;
    }

    @Override
    public Usuario obtenerUsuario(String nombreUsuario) {
        Usuario u =null;
        Session session = NewHibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("nombreUsuario", nombreUsuario));
         if(!criteria.list().isEmpty())
            u=(Usuario)criteria.list().get(0);
        session.getTransaction().commit();
        session.close();
        return u;
    }
    
    @Override
    public void modificarUsuario(Usuario unUsuario) {
       Session session= NewHibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       session.update(unUsuario);
       session.getTransaction().commit();
       session.close();
    }

    @Override
    public void crearUsuario(Usuario unUsuario) {
       Session session= NewHibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       session.save(unUsuario);
       session.getTransaction().commit();
       session.close();
    }

    @Override
    public void eliminarUsuario(Usuario unUsuario) {
       Session session= NewHibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
       session.update(unUsuario);
       session.getTransaction().commit();
       session.close();
    }

    @Override
    public List<Usuario> listaUsuario() {
        List<Usuario> listado = new ArrayList();
                Session session = NewHibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Usuario.class);
        criteria.add(Restrictions.like("estado", true));
         if(!criteria.list().isEmpty())
            listado=criteria.list();
        session.getTransaction().commit();
        session.close();
        return listado;
    }
}
