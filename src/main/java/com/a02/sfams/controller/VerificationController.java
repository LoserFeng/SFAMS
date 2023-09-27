package com.a02.sfams.controller;


import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Verification;
import com.a02.sfams.entity.vo.RecordQueryVo;
import com.a02.sfams.service.VerificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/verification")
public class VerificationController {


    @Autowired
    private VerificationService verificationService;
    @PostMapping("check")
    public Result check(@RequestBody  Verification verification){


        return verificationService.add(verification);
    }


    @ApiOperation("固定资产核查记录分页查询")
    @GetMapping("/page/{page}/{limit}")
    public Result page(@PathVariable Long page, @PathVariable Integer limit, RecordQueryVo recordQueryVo){

        return verificationService.page(page,limit,recordQueryVo);
    }

}
