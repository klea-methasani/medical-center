package org.example;

import org.example.config.HibernateUtils;
import org.example.dao.AppointmentDao;
import org.example.dao.DoctorDao;
import org.example.dao.UserDao;
import org.example.entities.Appointment;
import org.example.entities.User;
import org.example.static_data.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao userDao = new UserDao(session, transaction);
        DoctorDao doctorDao = new DoctorDao(session, transaction);
        User user = userDao.findById(3L);
        user.setUsername("user1");
        userDao.update(user);
        System.out.println(userDao.getAll());
    }
}