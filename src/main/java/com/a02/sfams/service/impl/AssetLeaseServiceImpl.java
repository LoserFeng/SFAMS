package com.a02.sfams.service.impl;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Asset;
import com.a02.sfams.entity.AssetLeaseRecord;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.vo.AssetVo;
import com.a02.sfams.entity.vo.RecordQueryVo;
import com.a02.sfams.mapper.AssetLeaseRecordMapper;
import com.a02.sfams.mapper.AssetMapper;
import com.a02.sfams.mapper.UserMapper;
import com.a02.sfams.service.AssetLeaseService;
import com.a02.sfams.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AssetLeaseServiceImpl  extends ServiceImpl<AssetLeaseRecordMapper, AssetLeaseRecord> implements AssetLeaseService {


    @Autowired
    private AssetMapper assetMapper;
    @Autowired
    private AssetLeaseRecordMapper assetLeaseRecordMapper;

    @Autowired
    private UserMapper userMapper;

    private static List<String> statusList= Lists.newArrayList("借出","闲置");

    @Override
    public Result lend(AssetLeaseRecord assetLeaseRecord) {
        Map<String,Object> resultMap=new HashMap<>();

        System.out.println("准备借出："+assetLeaseRecord);


        QueryWrapper<User>userQueryWrapper=new QueryWrapper<>();
        userQueryWrapper.eq("user_number",assetLeaseRecord.getUserNumber());
        User user=userMapper.selectOne(userQueryWrapper);

        if(user==null){
            return Result.fail("user:"+assetLeaseRecord.getUserNumber()+"不存在");
        }

        QueryWrapper<Asset>assetQueryWrapper=new QueryWrapper<>();
        assetQueryWrapper.eq("asset_number",assetLeaseRecord.getAssetNumber());


        Asset asset=assetMapper.selectOne(assetQueryWrapper);
        if(asset==null){
            return Result.fail("assetNumber:"+assetLeaseRecord.getAssetNumber()+"不存在");
        }
        if(asset.getStatus()==0){
            return Result.fail("assetNumber:"+assetLeaseRecord.getAssetNumber()+"正在被租借中");

        }




        assetLeaseRecord.setStartDate(new Date());
        if(assetLeaseRecord.getDueDate()==null){
            return Result.fail("请填写归还日期");
        }

        if(assetLeaseRecord.getDueDate().compareTo(assetLeaseRecord.getStartDate()) <=0){
            return Result.fail("归还日期不正确");
        }

        long diffDays=DateUtils.getDifferenceInDays(assetLeaseRecord.getStartDate(),assetLeaseRecord.getDueDate());
        if(diffDays>180){
            return Result.fail("归还日期不能大于半年");
        }



        assetLeaseRecord.setAssetId(asset.getId());

        assetLeaseRecord.setUserId(user.getId());

        assetLeaseRecord.setStatus(0);
        baseMapper.insert(assetLeaseRecord);
        asset.setStatus(0);
        assetMapper.updateById(asset);  //更新状态



        resultMap.put("assetLeaseRecord",assetLeaseRecord);
        return Result.ok(assetLeaseRecord);
    }

    @Override
    public Result revert(String assetNumber) {
        System.out.println(assetNumber);

        //检查基本信息

        QueryWrapper<Asset>assetQueryWrapper=new QueryWrapper<>();
        assetQueryWrapper.eq("asset_number",assetNumber);


        Asset asset=assetMapper.selectOne(assetQueryWrapper);
        if(asset==null){
            return Result.fail("assetNumber:"+assetNumber+"不存在");
        }
        if(asset.getStatus()==1){

            return Result.fail("assetNumber:"+assetNumber+"已经归还，无需重复归还");
        }

        QueryWrapper<AssetLeaseRecord>recordQueryWrapper=new QueryWrapper<>();

        recordQueryWrapper.eq("asset_id",asset.getId());
        recordQueryWrapper.eq("status",0);
        AssetLeaseRecord record=assetLeaseRecordMapper.selectOne(recordQueryWrapper);
        if(record==null){
            return Result.fail("没有该物品的借出记录");
        }


        //归还物品
        record.setReturnDate(new Date());
        record.setStatus(1);
        Integer count=assetLeaseRecordMapper.updateById(record);
        if(count<=0){
            return Result.fail("归还失败！");
        }


        asset.setStatus(1);
        count = assetMapper.updateById(asset);
        if(count<=0){
            return Result.fail("更新资产状态信息失败");
        }

        return Result.ok();
    }

    @Override
    public Result page(Long page, Integer limit, RecordQueryVo recordQueryVo) {
        Map<String,Object>resultMap=new HashMap<>();
        Page<AssetLeaseRecord> pageParam=new Page<>(page,limit);
        QueryWrapper<AssetLeaseRecord>wrapper=new QueryWrapper<>();

        if(!StringUtils.isEmpty(recordQueryVo.getAssetNumber())){
            wrapper.eq("asset_number",recordQueryVo.getAssetNumber());
        }

        if(!StringUtils.isEmpty(recordQueryVo.getUserNumber())){
            wrapper.eq("user_number",recordQueryVo.getUserNumber());
        }


        Page<AssetLeaseRecord>pageModel=baseMapper.selectPage(pageParam,wrapper);

        List<AssetLeaseRecord> records=pageModel.getRecords();

        List<AssetLeaseRecord> vo_records=new ArrayList<>();

        try {
            //将asset转换成视图对象
            for (AssetLeaseRecord record : records) {
//
//                assetVo.setAssetCategoryName(assetCategoryMapper.selectById(asset.getAssetCategoryId()).getCategoryName());
//                assetVo.setDepartmentName(departmentMapper.selectById(asset.getDepartmentId()).getDepartmentName());
//                assetVo.setStatusName(statusList.get(asset.getStatus()));
//                assetVo.setUserName(userMapper.selectById(asset.getUser()).getName());
//                vo_records.add(assetVo);
                Asset asset=assetMapper.selectById(record.getAssetId());
                User user=userMapper.selectById(record.getUserId());
                record.setUserNumber(user.getUserNumber());
                record.setAssetNumber(asset.getAssetNumber());
                record.setUserName(user.getName());
                record.setAssetName(asset.getAssetName());
                record.setStatusName(statusList.get(record.getStatus()));
                vo_records.add(record);
            }


        }catch (Exception e){
            System.out.println(e.toString());

            System.out.println("将asset转换成视图对象 有问题");
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
