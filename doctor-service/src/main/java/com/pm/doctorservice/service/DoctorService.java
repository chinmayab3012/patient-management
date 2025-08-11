package com.pm.doctorservice.service;

import com.pm.doctorservice.dto.DoctorRequestDTO;
import com.pm.doctorservice.dto.DoctorResponseDTO;
import com.pm.doctorservice.dto.PagedDoctorResponseDto;
import com.pm.doctorservice.entity.Doctor;
import com.pm.doctorservice.exception.EmailAlreadyExistException;
import com.pm.doctorservice.exception.EmailPhoneAlreadyExistsException;
import com.pm.doctorservice.exception.PhoneAlreadyExistException;
import com.pm.doctorservice.mapper.DoctorMapper;
import com.pm.doctorservice.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Transactional(value = Transactional.TxType.REQUIRED, rollbackOn = Exception.class)
    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        //check if doctor exist with email or phone
        boolean ifPresent = doctorRepository.existsByEmail(doctorRequestDTO.getEmail());
        if (ifPresent) {
            throw new EmailPhoneAlreadyExistsException("Email already exist ","email");
        }


        boolean isPresent = doctorRepository.existsByPhone(doctorRequestDTO.getPhone());
        if (isPresent) {
            throw new PhoneAlreadyExistException("Mobile already exist ","mobile");
        }

        Doctor doctor = doctorRepository.save(DoctorMapper.doctorRequestDtoToDoctor(doctorRequestDTO));

        return DoctorMapper.doctorToDoctorResponseDto(doctor);
    }

    public PagedDoctorResponseDto getDoctors(int page,int size,String sortValue,String sortField){
        Pageable pageable = PageRequest.of(page - 1, size,
                sortValue.equalsIgnoreCase("desc") ? Sort.by(sortField).descending()
                        : Sort.by(sortField).ascending());

        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

        List<DoctorResponseDTO> doctorResponseDTOStream =
                doctorPage.getContent().stream().map(DoctorMapper::doctorToDoctorResponseDto).toList();

       return PagedDoctorResponseDto.builder()
                .pageNumber(doctorPage.getNumber()+1)
                .pageSize(doctorPage.getSize())
                .totalElements((int)doctorPage.getTotalElements())
                .totalPages(doctorPage.getTotalPages())
                .doctors(doctorResponseDTOStream)
                .build();

    }


}
