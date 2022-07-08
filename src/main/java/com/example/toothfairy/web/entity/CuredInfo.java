package com.example.toothfairy.web.entity;


import com.example.toothfairy.web.dto.CuredInfoDto;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@ToString
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CuredInfo {

    @Id
    private int age;

    @Column private Long totalTreatmentDate; // 총 치료 기간 (milliseconds)
    @Column private Long totalWearTime;      // 총 착용 시간 (milliseconds)

    /*
     * 총 착용 시간 / 총 치료 기간
     *   => 시간 당 교정 일 수 예측 가능
     * */

    public static CuredInfo toEntity(CuredInfoDto dto){
        return CuredInfo.builder()
                .age(dto.getAge())
                .totalTreatmentDate(dto.getTotalTreatmentDate())
                .totalWearTime(dto.getTotalWearTime())
                .build();
    }
}
