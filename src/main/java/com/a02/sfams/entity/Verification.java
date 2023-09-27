package com.a02.sfams.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;


@EqualsAndHashCode(callSuper = true)
@Data
public class Verification extends BaseEntity{

    private Date verificationDate;

    private String location;

    private String verification_staff;

    private Integer status;
}
