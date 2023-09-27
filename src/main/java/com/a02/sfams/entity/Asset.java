package com.a02.sfams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
public class Asset extends BaseEntity{

    private String assetNumber;

    private String assetName;

    private String assetCategoryId;

    private BigDecimal originValue;

    private String specification;

    private String brandModel;

    private String assetPhoto;    //可选

    private String quantity;

    private int expectedLife;    //可选

    private Date acquisitionDate;

    private String Notes;

    private String user;

    @TableField(exist = false)
    private String userNumber;

    private String departmentId;

    private Integer status;


    @TableField(exist = false)
    private List<Verification> verificationList = new ArrayList<>();

    private BigDecimal netValue;

 //   private String fundingCard;




}
