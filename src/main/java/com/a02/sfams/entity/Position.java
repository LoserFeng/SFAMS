package com.a02.sfams.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Position extends BaseEntity {
    private String positionName;

}
