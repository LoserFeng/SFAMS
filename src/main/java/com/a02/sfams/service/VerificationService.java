package com.a02.sfams.service;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.Verification;
import com.a02.sfams.entity.vo.RecordQueryVo;

public interface VerificationService {

    Result add(Verification verification);

    Result page(Long page, Integer limit, RecordQueryVo recordQueryVo);
}
