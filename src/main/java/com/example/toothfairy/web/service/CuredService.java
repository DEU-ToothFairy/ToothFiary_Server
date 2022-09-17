package com.example.toothfairy.web.service;


import com.example.toothfairy.web.dto.CuredInfoDto;
import com.example.toothfairy.web.entity.CuredInfo;
import com.example.toothfairy.web.repository.CuredInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Transactional
@Service
public class CuredService {
    private CuredInfoRepository curedInfoRepository;

    private final int AVG_TREATMENT_DAY = 1005; // 비수술 주걱턱 교정 평균 치료 기간

    public CuredService(CuredInfoRepository curedInfoRepository) {
        this.curedInfoRepository = curedInfoRepository;
    }

    // 특정 날짜의 완치 환자 정보 반환
    public CuredInfoDto getCuredInfo(int age){
        CuredInfo curedInfo = curedInfoRepository.findById(age).orElse(null);

        if(Objects.isNull(curedInfo)){
            curedInfo = new CuredInfo(age, AVG_TREATMENT_DAY,0L,0L,0L);

            return createCuredInfo(CuredInfoDto.createDto(curedInfo));
        }
        
        return CuredInfoDto.createDto(curedInfo);
    }
    
    // 완치 환자 정보 추가 메소드
    // -> 특정 나이의 완치 환자 정보 요청이 왔을 떄 해당 나이의 완치 정보가 존재하지 않은 경우에만 등록 됨
    public CuredInfoDto createCuredInfo(CuredInfoDto curedInfoDto){
        CuredInfo result = curedInfoRepository.save(CuredInfo.toEntity(curedInfoDto));

        return Objects.isNull(result) ? null : CuredInfoDto.createDto(result);
    }
}
