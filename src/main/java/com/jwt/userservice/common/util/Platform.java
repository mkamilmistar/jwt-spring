package com.jwt.userservice.common.util;

import lombok.Getter;

@Getter
public enum Platform {

    BACKOFFICE("back-office"),
    IOS("ios"),
    ANDROID("android");

    private String code;

    Platform(String code) {
        this.code = code;
    }

}
