package com.pm.patientservice.mapper;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.exception.InvalidDateFormatException;
import com.pm.patientservice.model.Patient;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class PatientMapper
{
    public static PatientResponseDTO toDTO(Patient patient)
    {
        PatientResponseDTO patientDTO = new PatientResponseDTO();

        patientDTO.setId(patient.getId().toString());
        patientDTO.setName(patient.getName());
        patientDTO.setAddress(patient.getAddress());
        patientDTO.setEmail(patient.getEmail());
        patientDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientDTO;
    }

    public static Patient toModel(PatientRequestDTO  patientRequestDTO)
    {
        Patient patient = new Patient();

        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());

        LocalDate dob = parseAndValidateDate(patientRequestDTO.getDateOfBirth(), "dateOfBirth");
        LocalDate regDate = parseAndValidateDate(patientRequestDTO.getRegistrationDate(), "registrationDate");


        patient.setDateOfBirth(dob);
        patient.setRegistrationDate(regDate);


        return patient;
    }
    private static LocalDate parseAndValidateDate(String value, String fieldName) {
        LocalDate date;
        try {
            date = LocalDate.parse(value);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(fieldName, value);
        }

        return date;
    }

}
