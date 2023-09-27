package com.a02.sfams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
public class AssetLeaseRecord extends BaseEntity{

    private String assetId;

    @TableField(exist = false)
    private String assetNumber;
    @TableField(exist = false)
    private String assetName;

    private String userId;

    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String userNumber;

    private Date startDate;

    private Date dueDate;

    private Date returnDate;

    private Integer status;

    @TableField(exist = false)
    private String statusName;

    private String notes;

}
