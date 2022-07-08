package com.example.toothfairy.web.service;


import com.example.toothfairy.web.dto.CuredInfoDto;
import com.example.toothfairy.web.entity.CuredInfo;
import com.example.toothfairy.web.repository.CuredInfoRepository;
import org.springframework.stereotype.Service;

@Service
public class CuredService {
    private CuredInfoRepository curedInfoRepository;

    public CuredService(CuredInfoRepository curedInfoRepository) {
        this.curedInfoRepository = curedInfoRepository;
    }

    public CuredInfoDto getCuredInfo(int age){
        CuredInfo curedInfo = curedInfoRepository.findById(age)
                                                 .orElseThrow(() -> new IllegalArgumentException("완치자 정보 조회 오류"));

        return CuredInfoDto.createDto(curedInfo);
    }
}
