package org.example;

import org.example.config.HibernateUtils;
import org.example.dao.AppointmentDao;
import org.example.dao.DoctorDao;
import org.example.dao.ReportDao;
import org.example.dao.UserDao;
import org.example.entities.Appointment;
import org.example.entities.Doctor;
import org.example.entities.Report;
import org.example.entities.User;
import org.example.static_data.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSession();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDao userDao = new UserDao(session, transaction);
        DoctorDao doctorDao = new DoctorDao(session, transaction);
        AppointmentDao appointmentDao = new AppointmentDao(session, transaction);
        ReportDao reportDao = new ReportDao(session,transaction);
        User user = userDao.findById(3L);
      user.setUsername("user1");
       userDao.update(user);
       System.out.println(userDao.getAll());


userDao.delete(3L);
System.out.println(userDao.getAll());

       Doctor doctor= Doctor.builder()
        .username("doctor2")
        .specialization("ortoped")
        .name("doctor test")
       .build();
doctorDao.create(doctor);
doctorDao.create(doctor);
System.out.println(doctorDao.getAll());

        System.out.println(doctorDao.getAllBySpecialization("r"));
Doctor doctor1 =doctorDao.findById(2L);
Appointment appointment= Appointment.builder()
       .beginsAt(LocalDateTime.of(2023,11,13,12,00))
       .endsAt(LocalDateTime.of(2023,11,13,12,30))
       .patientName("pacienti1")
        .doctor(doctor)
        .note("test")
        .build();
appointmentDao.create(appointment);

appointmentDao.getByDoctorAndAfterNow(2L).forEach(System.out::println);
        Report report =Report.builder()
               .description("report note")
                .appointment(appointment)
               .build();
       reportDao.create(report);
       System.out.println(reportDao.getAll());



    }
}