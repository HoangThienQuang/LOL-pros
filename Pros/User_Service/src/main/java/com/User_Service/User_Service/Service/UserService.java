package com.User_Service.User_Service.Service;

import com.User_Service.User_Service.Entity.User;
import com.User_Service.User_Service.Repository.UserRepo;
import com.User_Service.User_Service.dto.request.UserCreateRequest;
import com.User_Service.User_Service.dto.respose.UserCreateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    @Autowired
    public UserService(UserRepo userRepo)
    {
        this.userRepo = userRepo;
    }

    public UserCreateResponse createUser(UserCreateRequest request)
    {
        User user = User.builder()
                .id(request.getId())
                .username(request.getUsername())
                .fullName(request.getFullName())
                .age(request.getAge())
                .city(request.getCity())
                .build();
        userRepo.save(user);
        return UserCreateResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .age(request.getAge())
                .city(user.getCity())
                .build();
    }

//    public UserInfoResponse getUserInfoByName(String name)
//    {
//        User user = userRepo.findByUsername(name).orElseThrow(()-> new AppException(ResponseCode.USER_NOT_EXIST));
//        return UserInfoResponse.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .age(user.getAge())
//                .build();
//    }
}
