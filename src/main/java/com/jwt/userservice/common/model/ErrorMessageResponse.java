package com.jwt.userservice.common.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessageResponse implements Serializable {

    private String errorMessageCode;

    private String errorMessageEn;

    private String errorTitleEn;

    private String errorMessageId;

    private String errorTitleId;
}
