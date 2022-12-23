package com.example.toothfairy.web.entity;

import com.example.toothfairy.web.dto.PatientDto;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;


@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Patient implements Serializable {
    @Id
    private String id;

    @Column(unique = true)
    private String patientId;

    @Column private String password;
    @Column private String name;
    @Column private Date birthDate;
    @Column private Date startDate;
    @Column private Date endDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "patientId", referencedColumnName = "patientId")
    private List<DailyWearTime> dailyWearTimeList; // 환자의 일별 착용 시간 리스트

    public static Patient toEntity(PatientDto dto){
        return Patient.builder()
                .id(dto.getId())
                .patientId(dto.getPatientId())
                .password(dto.getPassword())
                .name(dto.getName())
                .birthDate(dto.getBirthDate())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .dailyWearTimeList(dto.getDailyWearTimeList())
                .build();
    }
}
