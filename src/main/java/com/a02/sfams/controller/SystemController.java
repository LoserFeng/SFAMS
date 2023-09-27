package com.a02.sfams.controller;

import com.a02.sfams.dto.Result;
import com.a02.sfams.service.SystemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;
    @ApiOperation("文件上传至云")
    @PostMapping("/upload")
    public Result uploadFile(MultipartFile file)
    {
        String filePath= systemService.upload(file);
        return Result.ok(filePath);
    }
}
