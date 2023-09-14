package com.clinic.clinicpatients.service;

import java.util.List;

import com.clinic.clinicpatients.model.Patient;

public interface IPatientService {
    public List<Patient> findAll();

    public int save(Patient patient);

    public int update(Patient patient);

    public int deleteById(int patientID);
}
