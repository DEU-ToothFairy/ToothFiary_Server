package com.example.toothfairy.web.apiController;


import com.example.toothfairy.web.service.ToothBrushService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Controller
public class ToothBrushApiController {

    @Autowired
    ToothBrushService toothBrushService;

    @PostMapping("/upload2/{userId}")
    public @ResponseBody
    byte[] uploadFile(@PathVariable("userId") String userId , @RequestPart MultipartFile file) throws IOException
    {
        log.info("입력사진 : " + file.getOriginalFilename());
        toothBrushService.uploadFile(userId, file);
        byte[] data = toothBrushService.readFile();
        log.info("칫솔 - 사진응답완료");



        return data;

    }
}
