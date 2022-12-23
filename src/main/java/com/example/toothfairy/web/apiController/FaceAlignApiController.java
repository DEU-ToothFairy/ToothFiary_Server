package com.example.toothfairy.web.apiController;

import com.example.toothfairy.web.dto.PatientDto;
import com.example.toothfairy.web.dto.faceAlignDto;
import com.example.toothfairy.web.service.FaceAlignService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


@Slf4j
@Controller
public class FaceAlignApiController {

    @Autowired
    FaceAlignService faceAlignService;

    @PostMapping("/upload/{userId}")
    public @ResponseBody byte[] uploadFile(@PathVariable("userId") String userId , @RequestPart MultipartFile file) throws  IOException
    {
        log.info("입력사진 : " + file.getOriginalFilename());
        faceAlignService.uploadFile(userId, file);
        byte[] data = faceAlignService.readFile();

        log.info("주걱턱 - 사진응답완료");
         return data;

    }
    @GetMapping("/faceinfo")
    public ResponseEntity<faceAlignDto> readfaceInfo() throws FileNotFoundException {
        faceAlignDto facealignDto = faceAlignService.readtxtFile();
        log.info("좌표, 점수 응답완료");
        return ResponseEntity.status(HttpStatus.OK).body(facealignDto);
    }
}


