package com.example.toothfairy.web.apiController;


import com.example.toothfairy.web.dto.CuredInfoDto;
import com.example.toothfairy.web.entity.CuredInfo;
import com.example.toothfairy.web.service.CuredService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CuredApiController {

    private CuredService curedService;

    public CuredApiController(CuredService curedService) {
        this.curedService = curedService;
    }

    @GetMapping("/api/cured/{age}")
    public ResponseEntity<CuredInfo> getCuredInfo(@PathVariable int age){

        CuredInfoDto curedInfo = curedService.getCuredInfo(age);

        return ResponseEntity.status(HttpStatus.OK).body(CuredInfo.toEntity(curedInfo));
    }
}
