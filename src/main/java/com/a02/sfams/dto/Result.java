package com.a02.sfams.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private String message;
    private Object data;
    private Long total;
    private Integer code;

    public static Result ok(){
        return new Result(true, null, null, null,200);
    }
    public static Result ok(Object data){
        return new Result(true, null, data, null,200);
    }
    public static Result ok(List<?> data, Long total){
        return new Result(true, null, data, total,200);
    }
    public static Result fail(String message){
        return new Result(false, message, null, null,400);
    }
}
