package com.clinic.clinicpatients.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.clinic.clinicpatients.model.Patient;
import com.clinic.clinicpatients.model.ServiceResponse;
import com.clinic.clinicpatients.service.IPatientService;

import java.util.List;

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
    public ResponseEntity<EntityModel<ServiceResponse>> save(@RequestBody Patient patient) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iPatientService.save(patient);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Patient saved successfully");
        }

        EntityModel<ServiceResponse> responseEntity = EntityModel.of(serviceResponse);
        Link selfLink = Link.of("/api/v1/patients/save");
        responseEntity.add(selfLink);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<EntityModel<ServiceResponse>> update(@RequestBody Patient patient) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iPatientService.update(patient);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Patient updated successfully");
        }

        EntityModel<ServiceResponse> responseEntity = EntityModel.of(serviceResponse);
        Link selfLink = Link.of("/api/v1/patients/update");
        responseEntity.add(selfLink);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<EntityModel<ServiceResponse>> delete(@PathVariable int id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        int result = iPatientService.deleteById(id);
        if (result == 1) {
            serviceResponse.setSuccess(true);
            serviceResponse.setMessage("Patient deleted successfully");
        }

        EntityModel<ServiceResponse> responseEntity = EntityModel.of(serviceResponse);
        Link selfLink = Link.of("/api/v1/patients/delete/" + id);
        responseEntity.add(selfLink);

        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }
}
