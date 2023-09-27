package com.a02.sfams.service.impl;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.AssetCategory;
import com.a02.sfams.mapper.AssetCategoryMapper;
import com.a02.sfams.service.AssetCategoryService;
import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssetCategoryServiceImpl implements AssetCategoryService {

    @Autowired
    private AssetCategoryMapper assetCategoryMapper;

    /**
     * 获取资产分类列表
     * @return Result
     */
    @Override
    public Result getAssetCategoryList() {
        Map<String, Object> resultMap=new HashMap<>();
        List<AssetCategory> assetCategoryList=assetCategoryMapper.selectList(null);
        resultMap.put("assetCategoryList",assetCategoryList);
        return Result.ok(resultMap);
    }
}
