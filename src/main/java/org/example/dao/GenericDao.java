package org.example.dao;

import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public abstract class GenericDao<T, I> {
    private final Session session;
    private final Transaction transaction;
    private final Class<T> aClass;


    protected GenericDao(Session session, Transaction transaction, Class<T> aClass) {
        this.session = session;
        this.transaction = transaction;
        this.aClass = aClass;
    }

    protected T saveOrUpdate(T t){
        try{
            session.merge(t);
            transaction.commit();
            return t;
        } catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

    protected T findById(I id){
       return session.get(aClass, id);
    }

    protected List<T> findAll(){
        String query = String.format("select record from %s record", aClass.getSimpleName());
        Query<T> getAllQuery = session.createQuery(query, aClass);
        return getAllQuery.getResultList();
    }
    protected void delete(I id){
        T t = this.findById(id);
        session.remove(t);
        transaction.commit();
    }
}
