package com.a02.sfams.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Department extends BaseEntity {

    private String departmentNumber;

    private String departmentName;


}
