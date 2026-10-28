package com.JobPortal.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_logs")
public class TransactionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "system_source", nullable = false)
    private String systemSource; // e.g., "SALESFORCE", "SPRING_BOOT"

    @Column(name = "action_type", nullable = false)
    private String actionType; // e.g., "CREATE_COMPANY", "UPDATE_COMPANY"

    @Column(name = "status", nullable = false)
    private String status; // "SUCCESS" or "ERROR"

    @Column(name = "record_identifier")
    private String recordIdentifier; // The company email or Salesforce ID

    @Column(name = "details", columnDefinition = "TEXT")
    private String details; // The raw JSON payload or the error stack trace

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt; // Hibernate auto-fills this!

    // Constructors
    public TransactionLog() {}

    public TransactionLog(String systemSource, String actionType, String status, String recordIdentifier, String details) {
        this.systemSource = systemSource;
        this.actionType = actionType;
        this.status = status;
        this.recordIdentifier = recordIdentifier;
        this.details = details;
    }

    // Getters and Setters (Omitted for brevity - generate them in your IDE or use Lombok)
    public void setStatus(String status) {
        this.status = status;
    }
}