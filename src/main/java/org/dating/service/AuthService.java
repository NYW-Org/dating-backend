package org.dating.service;

import lombok.RequiredArgsConstructor;
import org.dating.model.request.OtpRequest;
import org.dating.model.request.ValidateOtpRequest;
import org.dating.model.response.MessageValidateResponse;
import org.dating.model.response.OtpDataResponse;
import org.dating.model.response.OtpValidateData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final JwtService jwtService;
  private final MessageCentralService messageCentralService;

  @Value("${default.phone-number}")
  private String adminPhone;

  @Value("${default.role}")
  private String adminRole;

  public String validateUser(ValidateOtpRequest validateOtpRequest) {
    MessageValidateResponse messageValidateResponse =
        messageCentralService.validateOtp(validateOtpRequest);
    if (messageValidateResponse.getResponseCode() != 200) {
      return null;
    }
    return jwtService.generateToken(
        messageValidateResponse.getData().getMobileNumber(), "User");
  }

  public OtpDataResponse sendOtp(OtpRequest otpRequest) {
    return messageCentralService.sendOtp(otpRequest.getPhoneNumber());
  }
}
