package com.Auth_Service.Auth_Service.Service;

import com.Auth_Service.Auth_Service.Entity.UserAccount;
import com.Auth_Service.Auth_Service.Exception.AppException;
import com.Auth_Service.Auth_Service.Exception.ResponseCode;
import com.Auth_Service.Auth_Service.Repository.AccountRepo;
import com.Auth_Service.Auth_Service.Repository.FeignRepo.UserClient;
import com.Auth_Service.Auth_Service.dto.request.CreateAccountRequest;
import com.Auth_Service.Auth_Service.dto.request.IntrospectRequest;
import com.Auth_Service.Auth_Service.dto.request.Login;
import com.Auth_Service.Auth_Service.dto.request.UserInfo;
import com.Auth_Service.Auth_Service.dto.response.AuthenticationResponse;
import com.Auth_Service.Auth_Service.dto.response.IntrospectResponse;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.*;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class AuthenticationService {

    @NonFinal
    protected static final String SIGN_KEY = "wR9DlNCvTRbRxPMIEyEUNfUwAm6dXQ6GIzUEsybNgMZ+POrcP4qfivKVXIy7vJqe";
    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
    private final AccountRepo accountRepo;
    private final UserClient userClient;

    @Autowired
    public AuthenticationService(AccountRepo accountRepo, UserClient userClient) {
        this.accountRepo = accountRepo;
        this.userClient = userClient;
    }

    public IntrospectResponse introspectResponse(IntrospectRequest request) throws ParseException, JOSEException {
        var token = request.getToken();

        JWSVerifier jwsVerifier = new MACVerifier(SIGN_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(jwsVerifier);

        return IntrospectResponse.builder()
                .valid(verified && expiryTime.after(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(Login request)
    {
        UserAccount user = accountRepo.findByUsername(
                request.getUsername()).orElseThrow(()->new AppException(ResponseCode.USER_NOT_EXIST));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean result = passwordEncoder.matches( request.getPassword(),user.getPassword());
        if (!result)
            throw new AppException(ResponseCode.PASSWORD_INCORRECT);

        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
                .authenticated(result)
                .token(token)
                .build();
    }

    private String generateToken(String username)
    {
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(username)
                .issuer("Quang")
                .issueTime(new Date())
                .expirationTime(new Date(Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()))

                .build();
        Payload payload = new Payload(claimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(SIGN_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Can not create token");
            throw new RuntimeException(e);
        }
    }

    public String createAccount(CreateAccountRequest request)
    {
        if (accountRepo.findByUsername(request.getUsername()).isPresent())
            throw new AppException(ResponseCode.USER_EXISTED);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        UserAccount userAccount = UserAccount.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        accountRepo.save(userAccount);

        UserInfo userInfo = UserInfo.builder()
                .id(userAccount.getId())
                .username(userAccount.getUsername())
                .fullName(request.getFirstName() + " " + request.getLastName())
                .age(request.getAge())
                .city(request.getCity())
                .build();

        userClient.createUserInfo(userInfo);



        return ResponseCode.SUCCESS_REQUEST.getMess();
    }
}
