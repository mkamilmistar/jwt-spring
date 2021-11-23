package com.jwt.userservice.common.util;

import lombok.Getter;

@Getter
public enum TokenScope {

  PRELOGIN("prelogin"),
  LOGIN("postlogin");

  private String value;

  TokenScope(String value) {
    this.value = value;
  }
}
