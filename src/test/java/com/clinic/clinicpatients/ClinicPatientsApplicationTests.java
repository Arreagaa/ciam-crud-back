package com.clinic.clinicpatients;

import com.clinic.clinicpatients.controller.PatientController;
import com.clinic.clinicpatients.model.Patient;
import com.clinic.clinicpatients.model.ServiceResponse;
import com.clinic.clinicpatients.service.IPatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClinicPatientsApplicationTests {

	@Autowired
	private PatientController patientController;

	@MockBean
	private IPatientService patientService;

	@BeforeEach
    public void setUp() {
        when(patientService.findAll()).thenReturn(new ArrayList<>());
        when(patientService.save(Mockito.any(Patient.class))).thenReturn(1); 
        when(patientService.update(Mockito.any(Patient.class))).thenReturn(1); 
        when(patientService.deleteById(Mockito.anyInt())).thenReturn(1); 
    }

	@Test
	public void testListPatients() {
		ResponseEntity<List<Patient>> responseEntity = patientController.list();
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		List<Patient> patients = responseEntity.getBody();
		assertNotNull(patients);
		assertEquals(0, patients.size());
	}

	@Test
	public void testSavePatient() {
		Patient patientToSave = new Patient();
		ResponseEntity<EntityModel<ServiceResponse>> responseEntity = patientController.save(patientToSave);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		EntityModel<ServiceResponse> serviceResponseEntityModel = responseEntity.getBody();
		assertNotNull(serviceResponseEntityModel);
		ServiceResponse serviceResponse = serviceResponseEntityModel.getContent();
		assertNotNull(serviceResponse);
		assertEquals("Patient saved successfully", serviceResponse.getMessage());
	}

	@Test
	public void testUpdatePatient() {
		Patient patientToUpdate = new Patient();
		ResponseEntity<EntityModel<ServiceResponse>> responseEntity = patientController.update(patientToUpdate);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		EntityModel<ServiceResponse> serviceResponseEntityModel = responseEntity.getBody();
		assertNotNull(serviceResponseEntityModel);

		ServiceResponse serviceResponse = serviceResponseEntityModel.getContent();
		assertNotNull(serviceResponse);
		assertEquals("Patient updated successfully", serviceResponse.getMessage());
	}

	@Test
	public void testDeletePatient() {
		ResponseEntity<EntityModel<ServiceResponse>> responseEntity = patientController.delete(1);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		EntityModel<ServiceResponse> serviceResponseEntityModel = responseEntity.getBody();
		assertNotNull(serviceResponseEntityModel);

		ServiceResponse serviceResponse = serviceResponseEntityModel.getContent();
		assertNotNull(serviceResponse);
		assertEquals("Patient deleted successfully", serviceResponse.getMessage());
	}

}
