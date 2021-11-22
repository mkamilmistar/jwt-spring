/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 22/11/2021
 */

package com.jwt.userservice.service;

import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.entity.User;
import com.jwt.userservice.model.request.GetUserRequest;
import com.jwt.userservice.model.response.GetUserResponse;
import com.jwt.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GetUserService implements BaseService<GetUserRequest, GetUserResponse> {

  private final UserRepository userRepository;

  @Override
  public GetUserResponse execute(GetUserRequest input) {
    User user = userRepository.findByUsername(input.getUsername());

    return GetUserResponse.builder().user(user).build();
  }
}
