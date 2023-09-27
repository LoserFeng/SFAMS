package com.a02.sfams.service;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.vo.QueryVo;

public interface UserService {

    Result login(User user);


    Result getUserInfo(String id);

    Result logout();

    Result page(Long page, Integer limit, QueryVo queryVo);

    Result query(QueryVo queryVo);
}
