package com.a02.sfams.service;


import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Asset;
import com.a02.sfams.entity.vo.QueryVo;


public interface AssetService {


    Result getAssetList();


    Result details(String id);


    Result page(Long page, Integer limit, QueryVo queryVo);

    Result remove(String id);

    Result add(Asset asset);

    Result getAssetById(String id);
}
