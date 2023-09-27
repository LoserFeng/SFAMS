package com.a02.sfams.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data

public class AssetCategory extends BaseEntity {

    private String categoryNumber;

    private String categoryName;


}
