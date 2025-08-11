package com.pm.doctorservice.controller;

import com.pm.doctorservice.dto.PagedAppointmentResponseDto;
import com.pm.doctorservice.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Tag(name = "appointment",description = "Api for getting appointment details")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @Operation(summary = "get appointment details")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedAppointmentResponseDto> getAppointmentByDateRange(
            @RequestParam LocalDateTime from,
            @RequestParam LocalDateTime to,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "endTime") String sortField
    ) {
        PagedAppointmentResponseDto appointmentResponseDto =
                appointmentService.getAppointments(from,to,page,size,sort,sortField);


        return ResponseEntity.ok().body(appointmentResponseDto);
    }


}
