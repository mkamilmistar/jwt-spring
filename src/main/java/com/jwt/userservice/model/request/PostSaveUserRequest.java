/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 22/11/2021
 */

package com.jwt.userservice.model.request;

import com.jwt.userservice.common.base.BaseRequest;
import com.jwt.userservice.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveUserRequest extends BaseRequest {
  private String name;
  private String username;
  private String password;
  private Collection<Role> roles;
}
