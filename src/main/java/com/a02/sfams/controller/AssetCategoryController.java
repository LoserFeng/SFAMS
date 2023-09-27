package com.a02.sfams.controller;

import com.a02.sfams.dto.Result;
import com.a02.sfams.service.AssetCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assetCategory")
@CrossOrigin
public class AssetCategoryController {



    @Autowired
    private AssetCategoryService assetCategoryService;
    @ApiOperation("固定资产列表")
    @GetMapping ("/list")
    public Result listAssetCategory(){
        return assetCategoryService.getAssetCategoryList();
    }
}
