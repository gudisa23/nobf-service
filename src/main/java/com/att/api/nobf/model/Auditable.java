package com.att.api.nobf.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.*;

import lombok.Data;

@Data
public abstract class Auditable {
    @CreatedBy
    protected String createdBy;

    @CreatedDate
    protected DateTime createdDate;

    @LastModifiedBy
    protected String lastModifiedBy;

    @LastModifiedDate
    protected DateTime lastModifiedDate;
}
