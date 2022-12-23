package com.example.toothfairy.web.service;


import com.example.toothfairy.web.dto.DailyWearTimeDto;
import com.example.toothfairy.web.entity.DailyWearTime;
import com.example.toothfairy.web.repository.DailyWearTimeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class DailyWearTimeService {
    private DailyWearTimeRepository dailyWearTimeRepository;

    public DailyWearTimeService(DailyWearTimeRepository dailyWearTimeRepository) {
        this.dailyWearTimeRepository = dailyWearTimeRepository;
    }

    /** 일일 착용 시간 리스트를 모두 저장하는 메소드 */
    public List<DailyWearTimeDto> saveDailyWearTimes(List<DailyWearTimeDto> wearTimes){
        List<DailyWearTime> entities = wearTimes.stream().map(wearTime -> DailyWearTime.toEntity(wearTime)).collect(Collectors.toList());

        entities = dailyWearTimeRepository.saveAll(entities);

        return entities.stream()
                .map(entity -> DailyWearTimeDto.createDto(entity))
                .collect(Collectors.toList());
    }
}
