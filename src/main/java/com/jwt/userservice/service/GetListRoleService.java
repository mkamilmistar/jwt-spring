package com.jwt.userservice.service;

import com.jwt.userservice.common.model.EmptyRequest;
import com.jwt.userservice.common.service.BaseService;
import com.jwt.userservice.model.entity.Role;
import com.jwt.userservice.model.response.GetListRoleResponse;
import com.jwt.userservice.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class GetListRoleService implements BaseService<EmptyRequest, GetListRoleResponse> {

  private final RoleRepository roleRepository;

  @Override
  public GetListRoleResponse execute(EmptyRequest input) {
    List<Role> list = roleRepository.findAll();

    return GetListRoleResponse.builder().roleList(list).build();
  }
}
