package com.example.toothfairy.web.dto;

import com.example.toothfairy.web.entity.CuredInfo;
import lombok.*;
import org.springframework.stereotype.Service;

import java.sql.Date;


@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CuredInfoDto {
    private int age;

    private int totalTreatmentDate; // 총 치료 기간 (milliseconds)
    private Long totalWearTime;      // 총 착용 시간 (milliseconds)

    public static CuredInfoDto createDto(CuredInfo entity){
        return CuredInfoDto.builder()
                .age(entity.getAge())
                .totalTreatmentDate(entity.getTotalTreatmentDate())
                .totalWearTime(entity.getTotalWearTime())
                .build();
    }

    // 1000 일 => 1000 * 24 * 60 * 60 * 1000
    // 3600 시간 => 3600 * 60 * 60 * 1000

    // 초 분 시간 일 월
}
