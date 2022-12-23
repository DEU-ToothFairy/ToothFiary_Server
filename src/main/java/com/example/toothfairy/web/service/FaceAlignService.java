package com.example.toothfairy.web.service;

import com.example.toothfairy.web.dto.faceAlignDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Scanner;

@Slf4j
@Transactional
@Service
public class FaceAlignService {


    @Transactional
    public void uploadFile(String userId, MultipartFile file) throws IOException {
        file.transferTo(new File("C:\\Users\\joon\\Desktop\\ToothFiary_Server-master\\ToothFiary_Server-master\\src\\main\\python\\face-align\\test-asset\\test.jpg"));
        runPython();
    }



    public void runPython()
    {
        log.info("주걱턱 - 파이썬 실행");

        String command;

        command = "python C:\\Users\\joon\\Desktop\\ToothFiary_Server-master\\ToothFiary_Server-master\\src\\main\\python\\face-align\\face-align.py";

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

    String path = "C:/Users/joon/Desktop/ToothFiary_Server-master/ToothFiary_Server-master/src/main/python/face-align/test-result/";

    public byte[] readFile()
    {
        byte[] data = new byte[0];
        String fileName = "test_graph.png";
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

    public faceAlignDto readtxtFile() throws FileNotFoundException {
        String txtfileName = "xy.txt";

        Scanner scanner = new Scanner(new File(path+txtfileName));    //좌표읽기

        String nose = scanner. next();
        String chin = scanner.next();
        double score = Double.parseDouble(scanner.next());
        int dots_num = Integer.parseInt(scanner.next());
        log.info(nose+" / " + chin);
        faceAlignDto faceinfo = new faceAlignDto(nose,chin,score,dots_num);

        return faceinfo;
    }

}
