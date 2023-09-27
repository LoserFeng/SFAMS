package com.a02.sfams.controller;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.AssetLeaseRecord;
import com.a02.sfams.entity.vo.QueryVo;
import com.a02.sfams.entity.vo.RecordQueryVo;
import com.a02.sfams.service.AssetLeaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/assetLease")
public class AssetLeaseController {



    @Autowired
    private AssetLeaseService assetLeaseService;

    @ApiOperation("资产出借")
    @PostMapping("/lend")
    public Result lend(@RequestBody AssetLeaseRecord assetLeaseRecord){

        return assetLeaseService.lend(assetLeaseRecord);
    }


    @ApiOperation("资产归还")
    @PostMapping("/revert/{assetNumber}")
    public Result revert(@PathVariable String assetNumber){

        return assetLeaseService.revert(assetNumber);
    }


    @ApiOperation("固定资产租借记录分页查询")
    @GetMapping("/page/{page}/{limit}")
    public Result page(@PathVariable Long page, @PathVariable Integer limit, RecordQueryVo recordQueryVo){
        return assetLeaseService.page(page,limit,recordQueryVo);
    }


}
