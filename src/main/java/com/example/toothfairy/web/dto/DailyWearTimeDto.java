package com.example.toothfairy.web.dto;

import com.example.toothfairy.web.entity.DailyWearTime;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DailyWearTimeDto {
    private String patientId;
    private Date wearDate;           // 착용 일자
    private Long totalWearTime;      // 하루 총 착용 시간 (milliseconds)

    public static DailyWearTimeDto createDto(DailyWearTime entity){
        return DailyWearTimeDto.builder()
                .patientId(entity.getPatientId())
                .wearDate(entity.getWearDate())
                .totalWearTime(entity.getTotalWearTime())
                .build();
    }
}
