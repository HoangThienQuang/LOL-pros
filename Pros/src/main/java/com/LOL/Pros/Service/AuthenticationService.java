package com.LOL.Pros.Service;

import com.LOL.Pros.Entity.User;
import com.LOL.Pros.Exception.AppException;
import com.LOL.Pros.Exception.ResponseCode;
import com.LOL.Pros.Repository.UserRepository;
import com.LOL.Pros.dto.request.LoginRequest;
import com.LOL.Pros.dto.response.AuthenResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    @Autowired
    private UserRepository userRepository;

    @NonFinal //no inject
    protected static final String SIGNER_KEY = "Yxf0kzIS3IMoBkMgXmqR1d0UEeD6BBHU8XProg5f0RP5uY1M0we+GlkGw3Rt9hye";

    public AuthenResponse checkAuthenticate(LoginRequest request)
    {
        User user = userRepository.findByUserName(request.getUserName()).orElseThrow(()-> new AppException(ResponseCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(request.getPassword(), user.getUserPass());

        if (!authenticated)
            throw new AppException(ResponseCode.NOT_AUTHENTICATED);
        var token = createToken(request.getUserName());

        return AuthenResponse.builder()
                .token(token)
                .authenticated(authenticated)
                .build();
    }

    private String createToken(String username)
    {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("localhost")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))
                .claim("custom", "custom claim")
                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create token");
            throw new RuntimeException(e);
        }
    }
}
