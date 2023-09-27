package com.a02.sfams.controller;


import com.a02.sfams.dto.Result;
import com.a02.sfams.service.AssetCategoryService;
import com.a02.sfams.service.DepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/department")
@RestController
@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;



    @ApiOperation("部门列表")
    @GetMapping ("/list")
    public Result listDepartment(){


        return departmentService.getDepartmentList();
    }

}
