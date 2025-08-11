package com.pm.doctorservice.repository;

import com.pm.doctorservice.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findByStartTimeBetween(LocalDateTime from, LocalDateTime to);

    Page<Appointment>  findByStartTimeBetween(LocalDateTime from, LocalDateTime to,Pageable pageable);

    /**
     * Custom native SQL query to find appointments within a time range.
     * `nativeQuery = true` indicates that this is a raw SQL query.
     * `:from` and `:to` are named parameters that will be mapped from the method arguments.
     * The table and column names should match your actual database schema.
     */
    @Query(value = "SELECT * FROM appointment a WHERE a.start_time BETWEEN :from AND :to", nativeQuery = true)
    List<Appointment> findAppointmentsByStartTimeNative(LocalDateTime from, LocalDateTime to);

    /**
     * Custom JPQL (Java Persistence Query Language) query.
     * JPQL operates on entities and their fields, not directly on database tables/columns.
     * `Appointment` refers to your entity class, and `startTime` refers to its field.
     */
    @Query("SELECT a FROM Appointment a WHERE a.startTime BETWEEN :from AND :to")
    List<Appointment> findAppointmentsByStartTimeJPQL(LocalDateTime from, LocalDateTime to);



}
