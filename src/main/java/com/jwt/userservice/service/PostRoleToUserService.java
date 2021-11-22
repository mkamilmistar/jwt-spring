/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 22/11/2021
 */

package com.jwt.userservice.service;

import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.entity.Role;
import com.jwt.userservice.model.entity.User;
import com.jwt.userservice.model.request.PostRoleToUserRequest;
import com.jwt.userservice.model.response.PostRoleToUserResponse;
import com.jwt.userservice.repository.RoleRepository;
import com.jwt.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostRoleToUserService implements BaseService<PostRoleToUserRequest, PostRoleToUserResponse> {

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;


  @Override
  public PostRoleToUserResponse execute(PostRoleToUserRequest input) {
    User user = userRepository.findByUsername(input.getUsername());
    Role role = roleRepository.findByName(input.getRoleName());
    user.getRoles().add(role);

    return PostRoleToUserResponse.builder().isSuccess(true).build();
  }
}
