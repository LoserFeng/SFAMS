package com.a02.sfams.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value="user")

public class User extends BaseEntity {




    private String userNumber;
    private String name;
    private String phone;


    private String password;
    private String mail;

    private String avatar;    //可选

    private String departmentId;

    private String positionId;

    private int authority;





}
