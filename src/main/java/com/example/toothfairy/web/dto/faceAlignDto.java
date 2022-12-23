package com.example.toothfairy.web.dto;

import com.example.toothfairy.web.entity.CuredInfo;
import lombok.*;


@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class faceAlignDto {
    private String nose;
    private String chin;
    private double score;
    private int dots_num;
}

