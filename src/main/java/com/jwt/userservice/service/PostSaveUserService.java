package com.jwt.userservice.service;

import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.request.PostSaveUserRequest;
import com.jwt.userservice.model.response.PostSaveUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostSaveUserService implements BaseService<PostSaveUserRequest, PostSaveUserResponse> {

  @Override
  public PostSaveUserResponse execute(PostSaveUserRequest input) {
    return null;
  }
}
