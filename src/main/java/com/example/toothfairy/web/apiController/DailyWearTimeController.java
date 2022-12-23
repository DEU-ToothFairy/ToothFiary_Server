package com.example.toothfairy.web.apiController;


import com.example.toothfairy.web.dto.DailyWearTimeDto;
import com.example.toothfairy.web.entity.DailyWearTime;
import com.example.toothfairy.web.service.DailyWearTimeService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class DailyWearTimeController {

    private DailyWearTimeService dailyWearTimeService;

    public DailyWearTimeController(DailyWearTimeService dailyWearTimeService) {
        this.dailyWearTimeService = dailyWearTimeService;
    }


    @PostMapping("/api/dailyWearTimes")
    public ResponseEntity<List<DailyWearTimeDto>> saveDailyWearTime(@RequestBody List<DailyWearTimeDto> dtos){
        log.info(dtos.toString());

        List<DailyWearTimeDto> result = dailyWearTimeService.saveDailyWearTimes(dtos);

        return result == null
                ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
                : ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
