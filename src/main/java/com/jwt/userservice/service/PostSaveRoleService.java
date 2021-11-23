package com.jwt.userservice.service;

import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.entity.Role;
import com.jwt.userservice.model.request.PostSaveRoleRequest;
import com.jwt.userservice.model.response.PostSaveRoleResponse;
import com.jwt.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostSaveRoleService implements BaseService<PostSaveRoleRequest, PostSaveRoleResponse> {

  private final RoleRepository roleRepository;

  @Override
  public PostSaveRoleResponse execute(PostSaveRoleRequest input) {

    Role role = Role.builder()
      .name(input.getRoleName())
      .build();

    role.setCreatedBy("SYSTEM");
    role.setUpdatedBy("SYSTEM");
    role.setUpdatedTime(new Timestamp(System.currentTimeMillis()));
    role.setCreatedTime(new Timestamp(System.currentTimeMillis()));

    log.info("Save ROLE to DB: {}", role.getName());

    roleRepository.save(role);

    return PostSaveRoleResponse.builder().isSuccess(true).build();
  }
}
