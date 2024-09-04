package org.example.dao;

import org.example.entities.User;
import org.example.exceptions.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDao extends GenericDao<User, Long> {
    private final Session session;
    private final Transaction transaction;

    public UserDao(Session session, Transaction transaction) {
        super(session, transaction, User.class);
        this.session = session;
        this.transaction = transaction;
    }

    public User create(User user) {
        if (user.getUserId() == null && this.findByUsername(user.getUsername()) == null) {
            return super.saveOrUpdate(user);
        } else if (this.findByUsername(user.getUsername()) != null) {
            throw GenericException.usernameExists(user.getUsername());
        } else {
            throw GenericException.idNotNull();
        }
    }

    public User update(User user) {
        if (user.getUserId() != null) {
            User existingUser = this.findById(user.getUserId());
            if (existingUser.getUsername().equals(user.getUsername())){
                return super.saveOrUpdate(user);
            } else if (this.findByUsername(user.getUsername()) == null){
                return super.saveOrUpdate(user);
            } else {
                throw GenericException.usernameExists(user.getUsername());
            }
        } else {
            throw GenericException.idIsNull();
        }
    }

    public List<User> getAll() {
        return super.findAll();
    }

    public User findById(Long id) {
        return super.findById(id);
    }

    public void delete(Long id) {
        super.delete(id);
    }

    public User findByUsername(String username) {
        String query = "select user from User user where user.username = :username";
        Query<User> findByUsernameQuery = session.createQuery(query, User.class);
        findByUsernameQuery.setParameter("username", username);
        return findByUsernameQuery.getSingleResultOrNull();
    }
}
