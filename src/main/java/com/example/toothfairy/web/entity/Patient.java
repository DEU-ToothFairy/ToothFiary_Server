package com.example.toothfairy.web.entity;

import com.example.toothfairy.web.dto.PatientDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;


@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient {

    @Id
    private String patientNum;

    @Column(unique = true) private String id;
    @Column private String password;
    @Column private String name;
    @Column private Date birthDate;
    @Column private Date startDate;
    @Column private Date endDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientNum")
    private List<DailyWearTime> dailyWearTimeList; // 환자의 일별 착용 시간 리스트

    public static Patient toEntity(PatientDto dto){
        return Patient.builder()
                .patientNum(dto.getPatientNum())
                .id(dto.getId())
                .password(dto.getPassword())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
