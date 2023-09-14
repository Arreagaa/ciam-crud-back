package com.clinic.clinicpatients.repository;

import java.util.List;

import com.clinic.clinicpatients.model.Patient;

public interface IPatientRepository {
    public List<Patient> findAll();

    public int save(Patient patient);

    public int update(Patient patient);

    public int deleteById(int patientID);
}