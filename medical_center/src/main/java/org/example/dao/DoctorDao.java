package org.example.dao;

import org.example.entities.Doctor;
import org.example.exceptions.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DoctorDao extends GenericDao<Doctor, Long> {
    private final Session session;
    private final Transaction transaction;

    public DoctorDao(Session session, Transaction transaction){
        super(session, transaction, Doctor.class);
        this.session = session;
        this.transaction = transaction;
    }

    public Doctor create(Doctor doctor){
        if (doctor.getDoctorId() == null){
            return super.saveOrUpdate(doctor);
        } else {
            throw GenericException.idNotNull();
        }
    }

    public Doctor update(Doctor doctor){
        if (doctor.getDoctorId() != null){
            return super.saveOrUpdate(doctor);
        } else {
            throw GenericException.idIsNull();
        }
    }

    public List<Doctor> getAll(){
        return super.findAll();
    }
    public Doctor findById(Long id){
        return super.findById(id);
    }
    public void delete(Long id){
        super.delete(id);
    }
}
