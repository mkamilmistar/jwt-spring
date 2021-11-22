package com.jwt.userservice.common.base;

import com.jwt.userservice.common.model.Pagination;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasePaginationResponse extends BaseResponse{

	private Pagination pagination;
}
