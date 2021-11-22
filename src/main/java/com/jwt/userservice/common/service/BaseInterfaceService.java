package com.jwt.userservice.common.service;


import com.jwt.userservice.common.base.BaseInterfaceRequest;
import com.jwt.userservice.common.base.BaseInterfaceResponse;

public interface BaseInterfaceService<T extends BaseInterfaceRequest, V extends BaseInterfaceResponse> {
    V execute(T input);
}
