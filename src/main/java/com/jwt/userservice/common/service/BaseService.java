package com.jwt.userservice.common.service;

import com.jwt.userservice.common.base.BaseRequest;
import com.jwt.userservice.common.base.BaseResponse;

public interface BaseService<T extends BaseRequest, V extends BaseResponse> {
  V execute(T input);
}
