package com.example.toothfairy.web.dto;


import lombok.*;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientLoginDto {
    private String id;
    private String password;
}
