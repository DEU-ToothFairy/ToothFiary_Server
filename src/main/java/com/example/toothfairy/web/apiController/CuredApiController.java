package com.example.toothfairy.web.apiController;


import com.example.toothfairy.web.dto.CuredInfoDto;
import com.example.toothfairy.web.entity.CuredInfo;
import com.example.toothfairy.web.service.CuredService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
public class CuredApiController {

    private CuredService curedService;

    public CuredApiController(CuredService curedService) {
        this.curedService = curedService;
    }

    @GetMapping("/api/cured/{age}")
    @ApiOperation(value = "완치 환자 정보 조회", notes = "나이에 해당하는 완치 환자 정보를 반환합니다.")
    public ResponseEntity<CuredInfo> getCuredInfo(@PathVariable int age){

        CuredInfoDto curedInfo = curedService.getCuredInfo(age);

        return ResponseEntity.status(HttpStatus.OK).body(CuredInfo.toEntity(curedInfo));
    }

    @PatchMapping("/api/cured/update")
    @ApiOperation(value = "완치 환자 정보 갱신", notes = "완치 환자의 정보를 갱신합니다.")
    public ResponseEntity<CuredInfoDto> updateCuredInfo(){
        return null;
    }

}
