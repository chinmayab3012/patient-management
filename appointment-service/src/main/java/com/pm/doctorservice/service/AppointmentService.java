package com.pm.doctorservice.service;

import com.pm.doctorservice.dto.AppointmentResponseDto;
import com.pm.doctorservice.dto.PagedAppointmentResponseDto;
import com.pm.doctorservice.entity.Appointment;
import com.pm.doctorservice.entity.CachedPatient;
import com.pm.doctorservice.repository.AppointmentRepository;
import com.pm.doctorservice.repository.CachedPatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final CachedPatientRepository cachedPatientRepository;

    public AppointmentService(AppointmentRepository appointmentRepository
            , CachedPatientRepository cachedPatientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.cachedPatientRepository = cachedPatientRepository;
    }

    public PagedAppointmentResponseDto getAppointments(LocalDateTime from,
                                                       LocalDateTime to,
                                                       Integer page,
                                                       Integer size,
                                                       String sort,
                                                       String sortField) {
        Pageable pageable = PageRequest.of(page - 1, size,
                sort.equalsIgnoreCase("desc") ? Sort.by(sortField).descending()
                        : Sort.by(sortField).ascending());

        Page<Appointment> appointmentPage = appointmentRepository.findByStartTimeBetween(from, to, pageable);

        List<AppointmentResponseDto> appointmentResponseDtos = appointmentPage.getContent().stream().map(appointment -> {
            String name = cachedPatientRepository.findById(appointment.getPatientId())
                    .map(CachedPatient::getFullName)
                    .orElse(null);
            return AppointmentResponseDto.builder()
                    .id(appointment.getUuid())
                    .endTime(appointment.getEndTime())
                    .patientId(appointment.getPatientId())
                    .patientName(name)
                    .startTime(appointment.getStartTime())
                    .reason(appointment.getReason())
                    .version(appointment.getVersion())
                    .build();
        }).toList();

        return PagedAppointmentResponseDto.builder()
                .appointmentResponseDtos(appointmentResponseDtos)
                .pageNumber(appointmentPage.getNumber() + 1)
                .pageSize(appointmentPage.getSize())
                .totalPages(appointmentPage.getTotalPages())
                .totalElements((int) appointmentPage.getTotalElements())
                .build();
    }


    public List<AppointmentResponseDto> getAppointmentsByDateRange(
            LocalDateTime from, LocalDateTime to
    ) {
        List<Appointment> appointments =
                appointmentRepository.findByStartTimeBetween(from, to);

        return appointments.stream().map(
                appointment -> {
                    String name = cachedPatientRepository.findById(appointment.getPatientId())
                            .map(CachedPatient::getFullName)
                            .orElse(null);
                    return AppointmentResponseDto.builder()
                            .id(appointment.getUuid())
                            .endTime(appointment.getEndTime())
                            .patientId(appointment.getPatientId())
                            .patientName(name)
                            .startTime(appointment.getStartTime())
                            .reason(appointment.getReason())
                            .version(appointment.getVersion())
                            .build();
                }
        ).toList();
    }
}
