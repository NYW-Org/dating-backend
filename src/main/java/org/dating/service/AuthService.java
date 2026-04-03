package org.dating.service;

import lombok.RequiredArgsConstructor;
import org.dating.model.request.LoginRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;

    @Value("${default.phone-number}")
    private String adminPhone;

    @Value("${default.role}")
    private String adminRole;

    public String validateUser(LoginRequest loginRequest) {
        if(!loginRequest.getPhoneNumber().equals(adminPhone)) {
            return null;
        }
        return jwtService.generateToken(adminPhone, adminRole);
    }
}
