package com.clinic.clinicpatients.repository;

import com.clinic.clinicpatients.model.Patient;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepository implements IPatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Patient> findAll() {
        String SQL = "SELECT * FROM patients";
        return jdbcTemplate.query(SQL, BeanPropertyRowMapper.newInstance(Patient.class));
    }

    @Override
    public int save(Patient patient) {
        String SQL = "INSERT INTO patients VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(SQL, new Object[] {
                patient.getPatientID(),
                patient.getName(),
                patient.getLastname(),
                patient.getIdentificationNumber(),
                patient.getDocumentType(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getDepartment(),
                patient.getMunicipality(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getPatientType()
        });
    }

    @Override
    public int update(Patient patient) {
        String SQL = "UPDATE patients SET name=?, lastname=?, identificationNumber=?, documentType=?, " +
                "birthDate=?, gender=?, address=?, department=?, municipality=?, phoneNumber=?, " +
                "email=?, patientType=? WHERE patientID=?";

        return jdbcTemplate.update(SQL, new Object[] {
                patient.getName(),
                patient.getLastname(),
                patient.getIdentificationNumber(),
                patient.getDocumentType(),
                patient.getBirthDate(),
                patient.getGender(),
                patient.getAddress(),
                patient.getDepartment(),
                patient.getMunicipality(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getPatientType(),
                patient.getPatientID()
        });
    }

    @Override
    public int deleteById(int patientID) {
        String SQL = "DELETE FROM patients WHERE PatientID=?";
        return jdbcTemplate.update(SQL, new Object[] { patientID });
    }
}
