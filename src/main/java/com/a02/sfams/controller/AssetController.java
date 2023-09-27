package com.a02.sfams.controller;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Asset;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.vo.AssetVo;
import com.a02.sfams.entity.vo.QueryVo;
import com.a02.sfams.service.AssetService;
import com.a02.sfams.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@CrossOrigin
@RestController
@RequestMapping("/asset")
public class AssetController {


    @Autowired
    private AssetService assetService;


    @ApiOperation("固定资产分页查询")
    @GetMapping("/page/{page}/{limit}")
    public Result page(@PathVariable Long page, @PathVariable Integer limit, QueryVo queryVo){
        return assetService.page(page,limit,queryVo);
    }

    @ApiOperation("固定资产删除")
    @DeleteMapping("/remove/{id}")
    public Result remove(@PathVariable String id){
        System.out.println("remove "+id);
        return assetService.remove(id);
    }


    @ApiOperation("固定资产录入或修改")
    @PostMapping("/input")
    public Result input(@RequestBody Asset asset){

        return assetService.add(asset);


    }
    @ApiOperation("固定资产详情")
    @GetMapping("/details/{id}")
    public Result input(@PathVariable String id){

        return assetService.getAssetById(id);


    }




}
