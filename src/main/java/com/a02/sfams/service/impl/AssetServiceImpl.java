package com.a02.sfams.service.impl;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Asset;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.vo.AssetVo;
import com.a02.sfams.entity.vo.QueryVo;
import com.a02.sfams.mapper.DepartmentMapper;
import com.a02.sfams.mapper.AssetCategoryMapper;
import com.a02.sfams.mapper.AssetMapper;
import com.a02.sfams.mapper.UserMapper;
import com.a02.sfams.service.AssetService;
import com.a02.sfams.service.UserService;
import com.a02.sfams.utils.DateUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class AssetServiceImpl extends ServiceImpl<AssetMapper, Asset> implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private AssetCategoryMapper assetCategoryMapper;

    @Autowired
    private UserService userService;



    private static List<String> statusList= Lists.newArrayList("在用","闲置","报废");

    @Override
    public Result getAssetList() {

        Map<String,Object>resultMap=new HashMap<>();




        return Result.ok(resultMap);
    }

    @Override
    public Result details(String id) {
        Map<String,Object>resultMap=new HashMap<>();



        return Result.ok(resultMap);
    }

    @Override
    public Result page(Long page, Integer limit, QueryVo queryVo) {
        Map<String,Object>resultMap=new HashMap<>();
        Page<Asset>pageParam=new Page<>(page,limit);
        QueryWrapper<Asset>wrapper=new QueryWrapper<>();
        if(!StringUtils.isEmpty(queryVo.getName())){
            wrapper.like("asset_name",queryVo.getName());
        }
        Page<Asset>pageModel=baseMapper.selectPage(pageParam,wrapper);
        List<Asset> records=pageModel.getRecords();


        List<AssetVo>vo_records=new ArrayList<>();

        try {
            //将asset转换成视图对象
            for (Asset asset : records) {
                AssetVo assetVo = new AssetVo(asset);
                System.out.println("assetVo:" + assetVo);
                assetVo.setAssetCategoryName(assetCategoryMapper.selectById(asset.getAssetCategoryId()).getCategoryName());
                assetVo.setDepartmentName(departmentMapper.selectById(asset.getDepartmentId()).getDepartmentName());
                assetVo.setStatusName(statusList.get(asset.getStatus()));
                assetVo.setUserName(userMapper.selectById(asset.getUser()).getName());
                vo_records.add(assetVo);

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

    @Override
    public Result remove(String id) {

        int num=assetMapper.deleteById(id);
        if(num==0){
            return Result.fail("ID:"+id+" 删除失败");
        }
        return Result.ok("iD:"+id+" 删除成功");
    }

    @Override
    public Result add(Asset asset) {
        Map<String,Object>resultMap=new HashMap<>();
        String id=asset.getId();
        if(StringUtils.isEmpty(id)){   //增加asset
            String assetName=asset.getAssetName();
            if(StringUtils.isEmpty(assetName)){
                return Result.fail("资产名不能为空!");
            }
//            QueryWrapper<Asset>wrapper=new QueryWrapper<>();
//            wrapper.eq("asset_name",assetName);
//            Integer count=baseMapper.selectCount(wrapper);
//            if(count>0){
//                return Result.fail("资产已经存在");
//            }
            asset.setAcquisitionDate(new Date());
            asset.setAssetNumber(createAssetNumber());
            User user=userMapper.selectOne(new QueryWrapper<User>().eq("user_number",asset.getUserNumber()));
            if(user==null){
                System.out.println("没有找到userNumber :"+asset.getUserNumber()+" 对应的用户");
                return Result.fail("没有找到userNumber :\"+asset.getUserNumber()+\" 对应的用户");
            }
            asset.setUser(user.getId());
            System.out.println("准备插入asset:"+asset.toString());
            baseMapper.insert(asset);
            id=asset.getId();
        }else{      //修改asset
            boolean res=checkUpdate(asset);
            if(res){
                Integer count = assetMapper.updateById(asset);
                if(count==0){
                    return Result.fail("更新失败！");
                }
            }
        }
        resultMap.put("asset",asset);

        return Result.ok(resultMap);
    }

    @Override
    public Result getAssetById(String id) {
        Map<String,Object> resultMap=new HashMap<>();


        Asset asset = assetMapper.selectById(id);
        if(asset==null){
            return Result.fail("查找失败");
        }

        User user=userMapper.selectById(asset.getUser());

        asset.setUserNumber(user.getUserNumber());

        resultMap.put("asset",asset);

        return Result.ok(resultMap);
    }

    private boolean checkUpdate( Asset asset) {
        return asset.getAssetName()!=null||asset.getAssetNumber()!=null||asset.getAssetCategoryId()!=null||asset.getBrandModel()!=null||asset.getNotes()!=null||asset.getAcquisitionDate()!=null||asset.getQuantity()!=null||asset.getDepartmentId()!=null||asset.getUser()!=null;

    }

    private String createAssetNumber() {
        String dateString = DateUtils.createDateString();
        //查询账户
        QueryWrapper<Asset> wrapper=new QueryWrapper<>();
        wrapper.like("asset_number",dateString+"%");
        wrapper.orderByDesc(" asset_number ");
        wrapper.last(" limit 1");
        Asset asset = assetMapper.selectOne(wrapper);
        if(asset==null){
            dateString+="0000";
        }else{
            String assetNumber = asset.getAssetNumber();
            Integer tail = Integer.valueOf(assetNumber.substring(dateString.length()));
            tail++;
            String space="";
            if(tail<10) space="000"+tail;
            else if(tail<100) space="00"+tail;
            else if(tail<1000) space="0"+tail;
            dateString+=space;
        }
        return dateString;
    }


}



