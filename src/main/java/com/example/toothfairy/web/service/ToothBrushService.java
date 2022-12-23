package com.example.toothfairy.web.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@Slf4j
@Transactional
@Service
public class ToothBrushService {

    @Transactional
    public void uploadFile(String userId, MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\Users\\joon\\Desktop\\ToothFiary_Server-master\\ToothFiary_Server-master\\src\\main\\python\\toothbrush\\test-asset\\test.jpg"));
        deleteOriginalFile();
        runPython();
    }

    public void deleteOriginalFile()
    {

        File file = new File("C:\\Users\\joon\\Desktop\\ToothFiary_Server-master\\ToothFiary_Server-master\\src\\main\\python\\toothbrush\\yolov5\\runs\\detect\\exp");

        if( file.exists() ) { //파일존재여부확인

            if(file.isDirectory()) { //파일이 디렉토리인지 확인
                log.info("기존 exp 파일 존재");
                File[] files = file.listFiles();

                for( int i=0; i<files.length; i++){
                    if( files[i].delete() ){
                        log.info("폴더 내부 파일 삭제 성공");
                    }else{
                        log.info("폴더 내부 파일 삭제 실패");
                    }
                }

            }
            if(file.delete()){
                log.info("파일삭제 성공");
            }else{
                log.info("파일삭제 실패");
            }
        }
        else{
            log.info("삭제할 파일이 존재하지 않습니다.");
        }
    }



    public void runPython()
    {
        log.info("칫솔 - 파이썬 실행");

        String command;

        command = "python C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/toothbrush/yolov5/detect.py --weights C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/toothbrush/ToothFairyModel/ToothFairyModel/yolov5s_results/weights/best.pt --img 320 --conf 0.5 --source C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/toothbrush/test-asset/test.jpg";

        try {
            Process process = Runtime.getRuntime().exec("cmd /c " + command);
            process.getErrorStream().close();
            process.getInputStream().close();
            process.getOutputStream().close();
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    String path = "C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/toothbrush/yolov5/runs/detect/exp/";

    public byte[] readFile()
    {
        byte[] data = new byte[0];
        String fileName = "test.jpg";   //출력물
        try {
            InputStream inputStream = new FileInputStream(path + fileName);

            long fileSize = new File(path + fileName).length();
            data = new byte[(int) fileSize];
            inputStream.read(data);
            inputStream.close();
        }catch (FileNotFoundException e) {
            log.info("파일없음");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}
