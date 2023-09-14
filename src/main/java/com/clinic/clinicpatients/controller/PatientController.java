package com.clinic.clinicpatients.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.clinicpatients.model.Patient;
import com.clinic.clinicpatients.model.ServiceResponse;
import com.clinic.clinicpatients.service.IPatientService;

@RestController
@RequestMapping("api/v1/patients")
@CrossOrigin("*")
public class PatientController {

    @Autowired
    private IPatientService iPatientService;

    @GetMapping("/list")
    public ResponseEntity<List<Patient>> list() {
        var result = iPatientService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ServiceResponse> save(@RequestBody Patient patient) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iPatientService.save(patient);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Patient saved successfully");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<ServiceResponse> update(@RequestBody Patient patient) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iPatientService.update(patient);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Patient updated successfully");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ServiceResponse> update(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iPatientService.deleteById(id);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Patient deleted successfully");
        }
        return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    }

}
