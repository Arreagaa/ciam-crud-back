package com.clinic.clinicpatients.model;

import lombok.Data;

@Data
public class Patient {
    private int patientID;
    private String name;
    private String lastname;
    private String identificationNumber;
    private String documentType;
    private String birthDate;
    private String gender;
    private String address;
    private String department;
    private String municipality;
    private String phoneNumber;
    private String email;
    private String patientType;
}
