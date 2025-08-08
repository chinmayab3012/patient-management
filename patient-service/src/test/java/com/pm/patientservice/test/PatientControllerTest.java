package com.pm.patientservice.test;

import com.pm.patientservice.controller.PatientController;
import com.pm.patientservice.dto.PagedPatientResponseDto;
import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
// docker build -t patient-service-image .  ( if this is success means test executed successfully)
// to run docker run -p 4000:4000 patient-service-image
class PatientControllerTest {

    private PatientService patientService;
    private PatientController patientController;

    @BeforeEach
    void setUp() {
        patientService = mock(PatientService.class);
        patientController = new PatientController(patientService);
    }

    @Test
    void testGetPatients() {
        PagedPatientResponseDto pagedResponse = PagedPatientResponseDto.builder().build();
        when(patientService.getPatients(1, 10, "asc", "name", "name", ""))
                .thenReturn(pagedResponse);

        ResponseEntity<PagedPatientResponseDto> response = patientController.getPatients(
                1, 10, "asc", "name", "name", "");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(pagedResponse, response.getBody());
        verify(patientService).getPatients(1, 10, "asc", "name", "name", "");
    }

    @Test
    void testCreatePatient() {
        PatientRequestDTO requestDTO = new PatientRequestDTO();
        PatientResponseDTO responseDTO = new PatientResponseDTO();
        when(patientService.createPatient(requestDTO)).thenReturn(responseDTO);

        ResponseEntity<PatientResponseDTO> response = patientController.createPatient(requestDTO);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertSame(responseDTO, response.getBody());
        verify(patientService).createPatient(requestDTO);
    }

    @Test
    void testUpdatePatient() {
        UUID id = UUID.randomUUID();
        PatientRequestDTO requestDTO = new PatientRequestDTO();
        PatientResponseDTO responseDTO = new PatientResponseDTO();
        when(patientService.updatePatient(id, requestDTO)).thenReturn(responseDTO);

        ResponseEntity<PatientResponseDTO> response = patientController.updatePatient(id, requestDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(responseDTO, response.getBody());
        verify(patientService).updatePatient(id, requestDTO);
    }

    @Test
    void testDeletePatient() {
        UUID id = UUID.randomUUID();

        ResponseEntity<Void> response = patientController.deletePatient(id);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
        verify(patientService).deletePatient(id);
    }
}