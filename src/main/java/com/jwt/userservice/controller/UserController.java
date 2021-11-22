/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 22/11/2021
 */

package com.jwt.userservice.controller;

import com.jwt.userservice.common.model.EmptyRequest;
import com.jwt.userservice.model.request.GetUserRequest;
import com.jwt.userservice.model.request.PostSaveUserRequest;
import com.jwt.userservice.model.response.GetListUserResponse;
import com.jwt.userservice.model.response.GetUserResponse;
import com.jwt.userservice.model.response.PostSaveUserResponse;
import com.jwt.userservice.service.GetListUserService;
import com.jwt.userservice.service.GetUserService;
import com.jwt.userservice.service.PostSaveUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user/v1")
@RequiredArgsConstructor
public class UserController {

  private final PostSaveUserService postSaveUserService;
  private final GetUserService getUserService;
  private final GetListUserService getListUserService;

  @PostMapping("/new")
  public ResponseEntity<PostSaveUserResponse> postUser(PostSaveUserRequest request) {
    return ResponseEntity.ok().body(postSaveUserService.execute(request));
  }

  @GetMapping("/{username}")
  public ResponseEntity<GetUserResponse> getUserByUsername(@PathVariable("username") String username) {
    GetUserRequest request = GetUserRequest.builder().username(username).build();
    return ResponseEntity.ok().body(getUserService.execute(request));
  }

  @GetMapping("/list")
  public ResponseEntity<GetListUserResponse> getUserByUsername() {
    return ResponseEntity.ok().body(getListUserService.execute(new EmptyRequest()));
  }

}
