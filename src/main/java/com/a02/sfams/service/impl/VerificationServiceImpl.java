package com.a02.sfams.service.impl;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Asset;
import com.a02.sfams.entity.Verification;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.Verification;
import com.a02.sfams.entity.vo.RecordQueryVo;
import com.a02.sfams.mapper.AssetMapper;
import com.a02.sfams.mapper.UserMapper;
import com.a02.sfams.mapper.VerificationMapper;
import com.a02.sfams.service.VerificationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class VerificationServiceImpl extends ServiceImpl<VerificationMapper, Verification> implements VerificationService {


    @Autowired
    private VerificationMapper verificationMapper;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AssetMapper assetMapper;


    private static List<String> statusList= Lists.newArrayList("在用","闲置","报废");

    @Override
    public Result add(Verification verification) {


        QueryWrapper<User>userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("user_number",verification.getUserNumber());

        User user=userMapper.selectOne(userQueryWrapper);

        if(user==null){
            return Result.fail("没有找到Usernumber:"+verification.getUserNumber()+"对应的User");
        }


        QueryWrapper<Asset>assetQueryWrapper=new QueryWrapper<>();
        assetQueryWrapper.eq("asset_number",verification.getAssetNumber());

        Asset asset=assetMapper.selectOne(assetQueryWrapper);
        if(asset==null){
            return Result.fail("没有找到AssetNumber:"+verification.getAssetNumber()+"对应的Asset");
        }

        verification.setVerificationStaff(user.getId());
        verification.setAssetId(asset.getId());

        verification.setVerificationDate(new Date());


        asset.setStatus(verification.getStatus());

        assetMapper.updateById(asset);

        baseMapper.insert(verification);


        return Result.ok();
    }

    @Override
    public Result page(Long page, Integer limit, RecordQueryVo recordQueryVo) {
        Map<String,Object> resultMap=new HashMap<>();
        Page<Verification> pageParam=new Page<>(page,limit);
        QueryWrapper<Verification>wrapper=new QueryWrapper<>();

        if(!StringUtils.isEmpty(recordQueryVo.getAssetNumber())){
            wrapper.eq("asset_number",recordQueryVo.getAssetNumber());
        }

        if(!StringUtils.isEmpty(recordQueryVo.getUserNumber())){
            wrapper.eq("user_number",recordQueryVo.getUserNumber());
        }


        Page<Verification>pageModel=baseMapper.selectPage(pageParam,wrapper);

        List<Verification> records=pageModel.getRecords();

        List<Verification> vo_records=new ArrayList<>();

        try {
            //将asset转换成视图对象
            for (Verification record : records) {
//
//                assetVo.setAssetCategoryName(assetCategoryMapper.selectById(asset.getAssetCategoryId()).getCategoryName());
//                assetVo.setDepartmentName(departmentMapper.selectById(asset.getDepartmentId()).getDepartmentName());
//                assetVo.setStatusName(statusList.get(asset.getStatus()));
//                assetVo.setUserName(userMapper.selectById(asset.getUser()).getName());
//                vo_records.add(assetVo);
                Asset asset=assetMapper.selectById(record.getAssetId());
                User user=userMapper.selectById(record.getVerificationStaff());
                record.setUserNumber(user.getUserNumber());
                record.setAssetNumber(asset.getAssetNumber());
                record.setUserName(user.getName());
                record.setAssetName(asset.getAssetName());
                record.setStatusName(statusList.get(record.getStatus()));
                vo_records.add(record);

            }


        }catch (Exception e){
            System.out.println(e.toString());

            System.out.println("将verification转换成视图对象 有问题");
            return Result.fail("将verification转换成视图对象 有问题");

        }

        System.out.println(vo_records);

        long pages=pageModel.getPages();
        long total=pageModel.getTotal();
        resultMap.put("records",vo_records);
        resultMap.put("total",total);
        resultMap.put("pages",pages);
        resultMap.put("current",page);
        return Result.ok(resultMap);
    }


}

