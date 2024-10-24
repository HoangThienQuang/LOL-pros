package com.LOL.Pros.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    NOT_IMPLEMENT_EXCEPTION(9999,"Uncategoried Error"),
    SUCCESS_STATUS(1000,"Request success"),
    USER_EXISTED(1102, "User Existed"),
    TOURNAMENT_NOT_EXIST(1006,"Tournament not exist"),
    USER_NOT_EXIST(1007,"Player not exist"),
    PASSWORD_INVALID(1103,"Password must be at least 8 characters"),
    USERNAME_INVALID(1104,"User name must be at least 3 characters"),
    ID_INVALID(1105,"Can not find any response match with input ID"),
    NOT_AUTHENTICATED(1108,"User was not authenticated")
    ;
    private final int code;
    private final String message;
}
