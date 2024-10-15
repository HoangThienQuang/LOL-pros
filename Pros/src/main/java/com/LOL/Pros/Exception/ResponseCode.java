package com.LOL.Pros.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    NOT_IMPLEMENT_EXCEPTION(9999,"Uncategoried Error"),
    SUCCESS_STATUS(1000,"Request success"),
    PLAYER_EXISTED(1001, "Player Existed"),
    TOURNAMENT_NOT_EXIST(1002,"Tournament not exist"),
    REGION_EXISTED(1001,"Region existed"),
    REGION_NOT_EXISTED(1002,"Region existed"),
    TEAM_NOT_EXIST(1002,"Team not exist"),
    TOURNAMENT_EXISTED(1001,"Tournament existed"),
    USER_NOT_EXIST(1002,"Player not exist"),
    PASSWORD_INVALID(1003,"Password must be at least 8 characters"),
    USERNAME_INVALID(1003,"User name must be at least 3 characters"),
    ID_INVALID(1003,"Can not find any response match with input ID"),
    NOT_AUTHENTICATED(1004,"User was not authenticated")
    ;
    private final int code;
    private final String message;
}
