/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 22/11/2021
 */

package com.jwt.userservice.controller;


import com.jwt.userservice.common.model.EmptyRequest;
import com.jwt.userservice.model.request.PostRoleToUserRequest;
import com.jwt.userservice.model.request.PostSaveRoleRequest;
import com.jwt.userservice.model.response.GetListRoleResponse;
import com.jwt.userservice.model.response.PostRoleToUserResponse;
import com.jwt.userservice.model.response.PostSaveRoleResponse;
import com.jwt.userservice.service.GetListRoleService;
import com.jwt.userservice.service.PostRoleToUserService;
import com.jwt.userservice.service.PostSaveRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("role/v1")
@RequiredArgsConstructor
public class RoleController {
  private final PostSaveRoleService postSaveRoleService;
  private final GetListRoleService getListRoleService;
  private final PostRoleToUserService postRoleToUserService;

  @PostMapping("/new")
  public ResponseEntity<PostSaveRoleResponse> postUser(PostSaveRoleRequest request) {
    return ResponseEntity.ok().body(postSaveRoleService.execute(request));
  }

  @GetMapping("/list")
  public ResponseEntity<GetListRoleResponse> getUserByUsername() {
    return ResponseEntity.ok().body(getListRoleService.execute(new EmptyRequest()));
  }

  @PostMapping("/save/role-user")
  public ResponseEntity<PostRoleToUserResponse> postUser(PostRoleToUserRequest request) {
    return ResponseEntity.ok().body(postRoleToUserService.execute(request));
  }
}
