package com.a02.sfams.utils;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

public class AssetCategoryNumberGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return IdGenerator.generateId();
    }


     public static void main(String[] args) {
        AssetCategoryNumberGenerator assetCategoryNumberGenerator=new AssetCategoryNumberGenerator();
        Number num=assetCategoryNumberGenerator.nextId(new Object());
        System.out.println(num);

    }

}
