package com.Auth_Service.Auth_Service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS_REQUEST(1000,"Request successfully"),
    UNCATCHED_ERROR(9999,"There is a mysterial error"),

    USER_NOT_EXIST(1001,"This user may not exist"),
    USER_EXISTED(1001,"This user already exist"),
    PASSWORD_INCORRECT(1001,"Wrong password")


    ;
    private int code;
    private String mess;
}
