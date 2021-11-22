package com.jwt.userservice.common.service;

import com.jwt.userservice.common.base.BasePaginationRequest;
import com.jwt.userservice.common.base.BasePaginationResponse;

public abstract class BasePaginationService<T extends BasePaginationRequest, V extends BasePaginationResponse> implements BaseService<T, V> {
}
