package com.a02.sfams.service.impl;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Department;
import com.a02.sfams.mapper.DepartmentMapper;
import com.a02.sfams.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentMapper departmentMapper;
    @Override
    public Result getDepartmentList() {

        Map<String,Object >resultMap=new HashMap<>();
        List<Department>departmentList=departmentMapper.selectList(null);
        resultMap.put("departmentList",departmentList);
        return Result.ok(resultMap);
    }
}
