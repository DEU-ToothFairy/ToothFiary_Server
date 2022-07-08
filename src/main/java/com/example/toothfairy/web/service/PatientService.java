package com.example.toothfairy.web.service;


import com.example.toothfairy.web.dto.PatientDto;
import com.example.toothfairy.web.entity.Patient;
import com.example.toothfairy.web.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDto getPatient(String patientNum){
        Patient patient = patientRepository.findById(patientNum)
                                           .orElseThrow(()-> new IllegalArgumentException("환자 정보 조회 오류"));

        return Objects.isNull(patient) ? null : PatientDto.createDto(patient);
    }
}
