package com.example.toothfairy.web.apiController;


import com.example.toothfairy.web.dto.PatientDto;
import com.example.toothfairy.web.entity.Patient;
import com.example.toothfairy.web.service.PatientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class PatientApiController {

    private PatientService patientService;

    public PatientApiController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/api/patients/{patientNum}")
    public ResponseEntity<Patient> getPatient(@PathVariable String patientNum){
        PatientDto patientDto = patientService.getPatient(patientNum);

        return ResponseEntity.status(HttpStatus.OK).body(Patient.toEntity(patientDto));
    }

    @GetMapping("/api/patients")
    public ResponseEntity<List<Patient>> getAllPatients(){
        List<PatientDto> patientDtos = patientService.getAllPatients();

        if(Objects.isNull(patientDtos)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        // Entity 리스트로 변환 후 리턴
        return ResponseEntity.status(HttpStatus.OK)
                             .body(patientDtos.stream()
                                              .map(patientDto -> Patient.toEntity(patientDto))
                                              .collect(Collectors.toList()));
    }

}
