package com.pm.patientservice.service;


import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.mapper.PatientMapper;
import com.pm.patientservice.model.Patient;
import com.pm.patientservice.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService
{
    private PatientRepository patientRepository;


    public PatientService(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO>  getPatients()
    {
        List<Patient> patients = patientRepository.findAll();

        // convert patient domain entity object to Data transfer object

        return patients.stream()
                .map(PatientMapper::toDTO).toList();
    }



    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO)
    {
        // we need to convert the patientRequestDto to patient entity model
        // basically we want to have same parameters to patient class but  in patientRequestDto id  is not present
       Patient newPatient = patientRepository.save(
               PatientMapper.toModel(patientRequestDTO)
       );
       return PatientMapper.toDTO(newPatient);
    }
}
