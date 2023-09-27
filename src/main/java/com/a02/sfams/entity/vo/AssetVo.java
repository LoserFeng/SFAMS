package com.a02.sfams.entity.vo;

import com.a02.sfams.entity.Asset;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetVo  {


    public AssetVo(Asset asset) {
        this.asset = asset;
    }

    private Asset asset;

    private String statusName;
    private String userNumber;
    private String departmentName;
    private String assetCategoryName;
    private String userName;

}
