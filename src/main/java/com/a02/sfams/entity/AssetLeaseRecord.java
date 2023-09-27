package com.a02.sfams.entity;

import java.util.Date;

public class AssetLeaseRecord extends BaseEntity{

    private String assetNumber;

    private String userId;

    private Date startDate;

    private Date dueDate;

    private Date returnDate;

    private Integer status;

    private String notes;

}
