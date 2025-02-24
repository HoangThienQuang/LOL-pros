package com.User_Service.User_Service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS_STATUS(1000,"Request successfully"),
    UNCATCHED_EXCEPTION(9999,"There is some mysterial exception"),

    USER_NOT_EXIST(1001,"This user may not exist"),


    USER_EXISTED(1002,"This user already exist")


    ;
    private final int code;
    private final String mess;
}
