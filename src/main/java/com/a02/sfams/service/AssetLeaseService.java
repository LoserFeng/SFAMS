package com.a02.sfams.service;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.AssetLeaseRecord;
import com.a02.sfams.entity.vo.RecordQueryVo;

public interface AssetLeaseService {
    Result lend(AssetLeaseRecord assetLeaseRecord);

    Result revert(String assetNumber);

    Result page(Long page, Integer limit, RecordQueryVo recordQueryVo);
}
