package org.example.dao;

import org.example.entities.Doctor;
import org.example.exceptions.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
        if (doctor.getDoctorId() == null  && this.getByUsername(doctor.getUsername())==null){
            return super.saveOrUpdate(doctor);
        } else if(this.getByUsername(doctor.getUsername())!=null) {
            throw GenericException.usernameExists(doctor.getUsername());
        } else{ throw GenericException.idNotNull();}
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

    public Doctor getByUsername(String username)
    { String query= "select doctor from Doctor doctor  where doctor.username= :username" ;
      Query<Doctor> getByUsernameQuery=session.createQuery(query , Doctor.class);
    getByUsernameQuery.setParameter("username" , username );
    return getByUsernameQuery.getSingleResultOrNull();}

    public List<Doctor> getAllBySpecialization (String specialization)
    {
        String query= " select doctor from Doctor doctor where doctor.specialization like :spec";
    Query <Doctor> getBySpecializationQuery= session.createQuery(query ,Doctor.class);
    getBySpecializationQuery.setParameter("spec" , "%".concat(specialization).concat("%"));
    return getBySpecializationQuery.getResultList();}


}
