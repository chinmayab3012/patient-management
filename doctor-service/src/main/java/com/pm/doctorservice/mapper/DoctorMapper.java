package com.pm.doctorservice.mapper;

import com.pm.doctorservice.dto.DoctorRequestDTO;
import com.pm.doctorservice.dto.DoctorResponseDTO;
import com.pm.doctorservice.entity.Doctor;

import java.time.LocalDateTime;

public class DoctorMapper {
        public static Doctor doctorRequestDtoToDoctor(DoctorRequestDTO doctorRequestDTO){
            Doctor doctor = new Doctor();
            doctor.setCreatedDate(LocalDateTime.now());
            doctor.setEmail(doctorRequestDTO.getEmail());
            doctor.setFullName(doctorRequestDTO.getFullName());
            doctor.setPhone(doctorRequestDTO.getPhone());
            doctor.setSpeciality(doctorRequestDTO.getSpeciality());
            doctor.setYearsOfExperience(doctorRequestDTO.getYearOfExperience());
            return doctor;
        }

        public static DoctorResponseDTO doctorToDoctorResponseDto(Doctor doctor){
            DoctorResponseDTO doctorResponseDTO = new DoctorResponseDTO();
            doctorResponseDTO.setId(doctor.getUuid().toString());
            doctorResponseDTO.setName(doctor.getFullName());
            doctorResponseDTO.setMobile(doctor.getPhone());
            doctorResponseDTO.setSpeciality(doctor.getSpeciality());
            doctorResponseDTO.setYearOfExperience(doctor.getYearsOfExperience());
            doctorResponseDTO.setEmail(doctor.getEmail());
            return doctorResponseDTO;
        }
}
