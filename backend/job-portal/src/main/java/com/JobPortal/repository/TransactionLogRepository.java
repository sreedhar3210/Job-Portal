package com.JobPortal.repository;

import com.JobPortal.models.TransactionLog;

import jakarta.persistence.EntityManager;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class TransactionLogRepository{

	// Spring Boot automatically injects the ready-to-use EntityManager here
    private final EntityManager entityManager;

    public TransactionLogRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public void saveTrxnLog(TransactionLog trxnLog) throws SQLException{
        System.out.println(">>>>>>> inside transactionLogRepository class saveTrxnLog method");
        entityManager.persist(trxnLog);
        System.out.println(">>>>>> inserted the Trxn log");
    }

}