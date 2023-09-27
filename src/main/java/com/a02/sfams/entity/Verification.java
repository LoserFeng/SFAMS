package com.a02.sfams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class Verification extends BaseEntity{
    private String assetId;

    @TableField(exist = false)
    private String assetNumber;

    @TableField(exist = false)
    private String assetName;


    private Date verificationDate;

    private String location;

    private String verificationStaff;

    @TableField(exist = false)
    private String userNumber;

    @TableField(exist = false)
    private String userName;

    private Integer status;

    @TableField(exist = false)
    private String statusName;
}
