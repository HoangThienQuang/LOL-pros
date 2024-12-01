package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.User;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.UserRepository;
import com.LOL.Pros.dto.request.Update.UserUpdateRequest;
import com.LOL.Pros.dto.request.UserCreateRequest;
import com.LOL.Pros.dto.response.UserResponse;
import com.LOL.Pros.dto.transferDTO.TransferUserGetAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    //create new user
    public UserResponse createNewUser(UserCreateRequest request)
    {
        if (userRepository.findByUserName(request.getUserName()).isPresent())
            throw new AppException(ResponseCode.USER_EXISTED);
        User user = new User();
        //mapping request to new user
        user.setUserName(request.getUserName());

        //encode password with Bcrypt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setUserPass(passwordEncoder.encode(request.getUserPass()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDob(request.getDob());

        userRepository.save(user);

        return UserResponse.builder()
                .userName(user.getUserName())
                .fullName(user.getFirstName() +" "+ user.getLastName())
                .dob(user.getDob())
                .build();
    }

    //get all user
    public List<TransferUserGetAll> getAllUser()
    {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::ToTransferUserGetALl).collect(Collectors.toList());
    }

    private TransferUserGetAll ToTransferUserGetALl(User user)
    {
        return TransferUserGetAll.builder()
                .userName(user.getUserName())
                .fullName(user.getFirstName() +" "+ user.getLastName())
                .dob(user.getDob())
                .build();
    }

    //get user by name
    public UserResponse getUserByName(String userName)
    {
        User user = userRepository.findByUserName(userName).orElseThrow(()-> new AppException(ResponseCode.USER_NOT_EXIST));
        return UserResponse.builder()
                .userName(user.getUserName())
                .fullName(user.getFirstName() +" "+ user.getLastName())
                .dob(user.getDob())
                .build();
    }
    //update user
    public UserResponse updateUserByUserName(UserUpdateRequest request)
    {
        User user = userRepository.findByUserName(request.getUserName()).orElseThrow(()-> new AppException(ResponseCode.USER_NOT_EXIST));
        //mapping

        user.setUserPass(request.getUserPass());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        userRepository.save(user);

        return UserResponse.builder()
                .userName(user.getUserName())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .dob(user.getDob())
                .build();
    }

    //delete user by user name
    public void deleteUserByName(String userName)
    {
        User user = userRepository.findByUserName(userName).orElseThrow(()-> new AppException(ResponseCode.USER_NOT_EXIST));
        userRepository.delete(user);
    }
}
