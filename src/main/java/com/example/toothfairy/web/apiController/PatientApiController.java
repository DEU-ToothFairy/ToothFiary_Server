package com.example.toothfairy.web.apiController;


import com.example.toothfairy.web.dto.PatientDto;
import com.example.toothfairy.web.dto.PatientLoginDto;
import com.example.toothfairy.web.entity.Patient;
import com.example.toothfairy.web.service.PatientService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/api/patients/{id}")
    @ApiOperation(value = "환자 정보 조회", notes = "환자의 ID를 통해 환자 정보를 반환 합니다.")
    public ResponseEntity<PatientDto> getPatient(@PathVariable String id){
        log.info(id);
        PatientDto patientDto = patientService.getPatient(id);

        return ResponseEntity.status(HttpStatus.OK).body(patientDto);
    }

    @GetMapping("/api/patients")
    @ApiOperation(value = "전체 환자 정보 조회", notes = "모든 환자의 정보를 반환 합니다.")
    public ResponseEntity<List<PatientDto>> getAllPatients(){
        List<PatientDto> patientDtos = patientService.getAllPatients();

        if(Objects.isNull(patientDtos)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        // Entity 리스트로 변환 후 리턴
        return ResponseEntity.status(HttpStatus.OK).body(patientDtos);
    }

    @PostMapping("/api/patients/add")
    @ApiOperation(value = "환자 등록", notes = "환자를 등록 합니다.")
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto){
        return null;
    }
    
    @PatchMapping("/api/patients/update")
    @ApiOperation(value = "환자 정보 수정", notes = "환자 정보를 수정 합니다.")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody PatientDto patientDto){
        return null;
    }

    @DeleteMapping("/api/patients/{patientNum}")
    @ApiOperation(value = "환자 정보 삭제", notes = "환자 정보를 삭제 합니다.")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable String patientNum){
        return null;
    }


    @PostMapping("/api/patients/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<PatientDto> login(@RequestBody PatientLoginDto loginDto){
        log.info(loginDto.toString());
        PatientDto result = patientService.login(loginDto);

        return Objects.isNull(result)
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
