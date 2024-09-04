package org.example.dao;

import org.example.entities.Appointment;
import org.example.exceptions.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDao extends GenericDao<Appointment, Long> {
    private final Session session;
    private final Transaction transaction;

    public AppointmentDao(Session session, Transaction transaction){
        super(session, transaction, Appointment.class);
        this.session = session;
        this.transaction = transaction;
    }

    public Appointment create(Appointment appointment){
        if (appointment.getAppointmentId() == null) {
            return super.saveOrUpdate(appointment);
        } else {
            throw GenericException.idNotNull();
        }
    }

    public Appointment update(Appointment appointment){
        if (appointment.getAppointmentId() != null){
            return super.saveOrUpdate(appointment);
        } else {
            throw GenericException.idIsNull();
        }
    }

    public Appointment getById(Long id){
        return super.findById(id);
    }

    public List<Appointment> getAll(){
        return super.findAll();
    }

    public void delete(Long id){
        super.delete(id);
    }

    public List<Appointment> getByDoctorAndAfterNow(Long doctorId){
        String query = "select appointment from Appointment appointment " +
                "where appointment.doctor.doctorId = :doctorId and appointment.beginsAt > :now";
        Query<Appointment> getByDoctorQuery = session.createQuery(query, Appointment.class);
        getByDoctorQuery.setParameter("doctorId", doctorId);
        getByDoctorQuery.setParameter("now", LocalDateTime.now());
        return getByDoctorQuery.getResultList();
    }

}
