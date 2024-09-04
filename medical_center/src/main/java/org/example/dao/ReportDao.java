package org.example.dao;

import org.example.entities.Report;
import org.example.exceptions.GenericException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ReportDao extends GenericDao<Report, Long>{
    private final Session session;
    private final Transaction transaction;

    public ReportDao(Session session, Transaction transaction){
        super(session, transaction, Report.class);
        this.session = session;
        this.transaction = transaction;
    }

    public Report create(Report report){
        if (report.getReportId() == null){
            return super.saveOrUpdate(report);
        } else {
            throw GenericException.idNotNull();
        }
    }
    public Report update(Report report){
        if (report.getReportId() != null){
            return super.saveOrUpdate(report);
        } else {
            throw GenericException.idIsNull();
        }
    }

    public List<Report> getAll(){
        return super.findAll();
    }

    public Report findById(Long id){
        return super.findById(id);
    }

    public void delete(Long id){
        super.delete(id);
    }
}
