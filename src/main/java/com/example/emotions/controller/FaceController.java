package com.example.emotions.controller;

import com.example.emotions.common.ApiResponse;
import com.example.emotions.util.BApiUtil;
import com.example.emotions.vo.FaceInfo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/face")
@CrossOrigin
public class FaceController {
    @RequestMapping("/process")
    ApiResponse<FaceInfo> process(@RequestParam("file") MultipartFile file) throws IOException {
        FaceInfo faceInfo = BApiUtil.processFace(file.getBytes());
        return ApiResponse.<FaceInfo>builder().status(0).msg("ok").data(faceInfo).build();
    }
}
