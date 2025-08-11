package com.pm.doctorservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID uuid;

    @NotNull(message = "patient id is required")
    @Column(nullable = false)
    private UUID patientId;

    @NotNull(message = "start time is required")
    @Column(nullable = false)
    @Future(message = "start time must be in future")
    private LocalDateTime startTime;

    @NotNull(message = "end time is required")
    @Column(nullable = false)
    @Future(message = "end time must be in future")
    private LocalDateTime endTime;

    @NotNull(message = "reason is required")
    @Size(min = 10,max = 255,message = "reason must be min 10 and max 255 characters")
    @Column(nullable = false)
    private String reason;

    @Version
    @Column(nullable = false)
    private long version;

    public Appointment() {}

    public Appointment(UUID patientId, String reason, LocalDateTime startTime, LocalDateTime endTime) {
        this.patientId = patientId;
        this.reason = reason;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public void setPatientId(UUID patientId) {
        this.patientId = patientId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
