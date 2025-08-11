package com.pm.doctorservice.controller;

import com.pm.doctorservice.dto.DoctorRequestDTO;
import com.pm.doctorservice.dto.DoctorResponseDTO;
import com.pm.doctorservice.dto.PagedDoctorResponseDto;
import com.pm.doctorservice.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@Valid @RequestBody DoctorRequestDTO doctorRequestDTO){
        DoctorResponseDTO doctor = doctorService.createDoctor(doctorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(doctor);
    }

    @GetMapping
    public ResponseEntity<PagedDoctorResponseDto> getDoctors(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "speciality") String sortField
    ){
        PagedDoctorResponseDto doctors = doctorService.getDoctors(page, size, sort, sortField);
        return ResponseEntity.ok().body(doctors);
    }


}
