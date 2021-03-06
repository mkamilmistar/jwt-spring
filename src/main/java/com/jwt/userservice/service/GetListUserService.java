package com.jwt.userservice.service;

import com.jwt.userservice.common.model.EmptyRequest;
import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.entity.User;
import com.jwt.userservice.model.response.GetListUserResponse;
import com.jwt.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GetListUserService implements BaseService<EmptyRequest, GetListUserResponse> {

  private final UserRepository userRepository;

  @Override
  public GetListUserResponse execute(EmptyRequest input) {

    List<User> list = userRepository.findAll();

    return GetListUserResponse.builder().userList(list).build();
  }
}
