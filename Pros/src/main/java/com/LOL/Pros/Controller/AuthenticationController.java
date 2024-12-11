package com.LOL.Pros.Controller;

import com.LOL.Pros.Service.AuthenticationService;
import com.LOL.Pros.dto.request.LoginRequest;
import com.LOL.Pros.dto.response.ApiResponse;
import com.LOL.Pros.dto.response.AuthenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<AuthenResponse> authenticateLogin(@RequestBody LoginRequest request)
    {
        return ApiResponse.<AuthenResponse>builder()
                .data(authenticationService.checkAuthenticate(request))
                .build();
    }
}
