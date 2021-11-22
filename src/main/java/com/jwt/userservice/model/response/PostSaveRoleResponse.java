/*
 * @Author M. Kamil
 * mk.kamiil@gmail.com
 * 22/11/2021
 */

package com.jwt.userservice.model.response;

import com.jwt.userservice.common.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostSaveRoleResponse extends BaseResponse {
  Boolean isSuccess;
}
