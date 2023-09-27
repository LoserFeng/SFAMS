package com.a02.sfams.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class AssetMaintenanceRecord extends BaseEntity {
    private String assetId;

    private Date maintenanceDate;

    private BigDecimal MaintenanceCost;

    private String MaintenanceDescription;

}
