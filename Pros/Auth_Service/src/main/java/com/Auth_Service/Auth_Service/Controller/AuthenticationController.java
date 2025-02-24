package com.Auth_Service.Auth_Service.Controller;

import com.Auth_Service.Auth_Service.Exception.ResponseCode;
import com.Auth_Service.Auth_Service.Service.AuthenticationService;
import com.Auth_Service.Auth_Service.dto.ApiResponse;
import com.Auth_Service.Auth_Service.dto.request.CreateAccountRequest;
import com.Auth_Service.Auth_Service.dto.request.IntrospectRequest;
import com.Auth_Service.Auth_Service.dto.request.Login;
import com.Auth_Service.Auth_Service.dto.response.AuthenticationResponse;
import com.Auth_Service.Auth_Service.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/Auth")
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService)
    {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/log-in")
    ApiResponse<AuthenticationResponse> authenticate(@RequestBody Login request)
    {
        //boolean result = authenticationService.authenticate(request);
        return ApiResponse.<AuthenticationResponse>builder()
                .code(1000)
                .mess(ResponseCode.SUCCESS_REQUEST.getMess())
                .data(authenticationService.authenticate(request))
                .build();
    }

    @PostMapping("/introspect")
    ApiResponse<IntrospectResponse> introspect(@RequestBody IntrospectRequest request) throws ParseException, JOSEException {
        return ApiResponse.<IntrospectResponse>builder()
                .code(1000)
                .mess(ResponseCode.SUCCESS_REQUEST.getMess())
                .data(authenticationService.introspectResponse(request))
                .build();
    }

    @PostMapping("/create")
    ApiResponse<Object> createAccount(@RequestBody CreateAccountRequest request){
        return ApiResponse.builder()
                .code(1000)
                .mess(ResponseCode.SUCCESS_REQUEST.getMess())
                .data(authenticationService.createAccount(request))
                .build();
    }
}
