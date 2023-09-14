package com.clinic.clinicpatients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.clinic.clinicpatients.model.Patient;
import com.clinic.clinicpatients.repository.IPatientRepository;

import java.util.List;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository iPatientRepository;

    @Override
    public List<Patient> findAll() {
        List<Patient> list;
        try {
            list = iPatientRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
        return list;
    }

    @Override
    public int save(Patient patient) {
        int row;
        try {
            row = iPatientRepository.save(patient);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int update(Patient patient) {
        int row;
        try {
            row = iPatientRepository.update(patient);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }

    @Override
    public int deleteById(int id) {
        int row;
        try {
            row = iPatientRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
        return row;
    }
}
