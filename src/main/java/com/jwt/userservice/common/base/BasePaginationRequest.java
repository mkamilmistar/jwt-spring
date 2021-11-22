package com.jwt.userservice.common.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasePaginationRequest extends BaseRequest{
    private Integer pageSize;
    private Integer pageNumber;
    private String sortBy;
    private String sortType;
}

