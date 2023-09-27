package com.a02.sfams.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BaseEntity {

    @ApiModelProperty(value = "id主键")
    private String id;


    //这个不知道有啥用，暂且不加了
//    @TableField(exist = false)
//    private Map<String,Object> param=new HashMap<>();

}
