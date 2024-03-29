package com.example.toothfairy.web.dto;


import com.example.toothfairy.web.entity.DailyWearTime;
import com.example.toothfairy.web.entity.Patient;
import lombok.*;

import java.sql.Date;
import java.util.List;


@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDto {
    private String id;
    private String patientId;
    private String password;
    private String name;
    private Date birthDate;
    private Date startDate;
    private Date endDate;

    // 환자의 일별 착용 시간 리스트
    private List<DailyWearTime> dailyWearTimeList;
    
    // 엔티티를 Dto로 변환
    public static PatientDto createDto(Patient patient){
        return PatientDto.builder()
                .id(patient.getId())
                .patientId(patient.getPatientId())
                .password(patient.getPassword())
                .name(patient.getName())
                .birthDate(patient.getBirthDate())
                .startDate(patient.getStartDate())
                .endDate(patient.getEndDate())
                .dailyWearTimeList(patient.getDailyWearTimeList())
                .build();
    }
}
