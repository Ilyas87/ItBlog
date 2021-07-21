package org.itstep.Blog.db;

import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.itstep.Blog.entity.User;

import javax.persistence.*;


@Log
public class DbManager {

    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    public static User getUserByEmail(final String email){
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        User user = null;

        try{
            transaction = manager.getTransaction();
            transaction.begin();
            Query query = manager.createQuery("from User u where u.email= : email");
            user = (User) query.setParameter("email", email).getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return user;
    }

    public static boolean addOrUpdateUser(final Operation operation, final User user) {
        Boolean flag = false;
        EntityManager manager = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = manager.getTransaction();
            transaction.begin();
            if (operation.equals(Operation.Create)){
                manager.persist(user);
            } else if(operation.equals(Operation.Update)){
                manager.merge(user);
            }
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
        } finally {
            manager.close();
        }

        return flag;
    }
}
