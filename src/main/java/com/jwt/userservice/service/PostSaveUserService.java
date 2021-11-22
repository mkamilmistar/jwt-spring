package com.jwt.userservice.service;

import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.entity.User;
import com.jwt.userservice.model.request.PostSaveUserRequest;
import com.jwt.userservice.model.response.PostSaveUserResponse;
import com.jwt.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostSaveUserService implements BaseService<PostSaveUserRequest, PostSaveUserResponse> {

  private final UserRepository userRepository;

  @Override
  public PostSaveUserResponse execute(PostSaveUserRequest input) {

    User user = User.builder()
      .name(input.getName())
      .username(input.getUsername())
      .password(input.getPassword())
      .build();

    log.info("Save user to DB: {}", user.getUsername());

    userRepository.save(user);

    return PostSaveUserResponse.builder().isSuccess(true).build();
  }
}
