package com.example.toothfairy.web.service;


import com.example.toothfairy.web.dto.PatientDto;
import com.example.toothfairy.web.dto.PatientLoginDto;
import com.example.toothfairy.web.entity.Patient;
import com.example.toothfairy.web.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    // 특정 환자의 정보를 가져오는 메소드
    public PatientDto getPatient(String id){
        Patient patient = patientRepository.findByUserId(id)
                                           .orElseThrow(()-> new IllegalArgumentException("환자 정보 조회 오류"));

        return Objects.isNull(patient) ? null : PatientDto.createDto(patient);
    }

    // 모든 환자들의 정보를 가져오는 메소드
    public List<PatientDto> getAllPatients(){
        List<PatientDto> patients = patientRepository.findAll()
                                                     .stream().map(patient -> PatientDto.createDto(patient))
                                                     .collect(Collectors.toList());

        return patients;
    }

    public PatientDto login(PatientLoginDto loginDto){
        Patient patient = patientRepository.findByUserId(loginDto.getId()).orElse(null);

        
        // 해당 아이디가 존재하지 않는 경우
        if(Objects.isNull(patient)) return null;
        // 비밀번호가 틀린 경우
        if(!patient.getPassword().equals(loginDto.getPassword())) return null;

        return PatientDto.createDto(patient);
    }
}
