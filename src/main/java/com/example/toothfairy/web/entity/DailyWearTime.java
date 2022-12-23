package com.example.toothfairy.web.entity;

import com.example.toothfairy.web.dto.DailyWearTimeDto;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;


@ToString
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DailyWearTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "patientId")
    private String patientId;

    @Column private Date wearDate;           // 착용 일자
    @Column private Long totalWearTime;      // 하루 총 착용 시간 (milliseconds)

    public static DailyWearTime toEntity(DailyWearTimeDto dto){
        return DailyWearTime.builder()
                .patientId(dto.getPatientId())
                .wearDate(dto.getWearDate())
                .totalWearTime(dto.getTotalWearTime())
                .build();
    }
}
