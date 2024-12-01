package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.User;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.UserRepository;
import com.LOL.Pros.dto.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository userRepository;

    public boolean checkAuthenticate(LoginRequest request)
    {
        User user = userRepository.findByUserName(request.getUserName()).orElseThrow(()-> new AppException(ResponseCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        return passwordEncoder.matches(request.getPassword(), user.getUserPass());
    }
}
