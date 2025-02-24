package com.User_Service.User_Service.Exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppException extends RuntimeException{
    private ResponseCode responseCode;

    public AppException(ResponseCode responseCode)
    {
        super(responseCode.getMess());
        this.responseCode = responseCode;
    }
}
