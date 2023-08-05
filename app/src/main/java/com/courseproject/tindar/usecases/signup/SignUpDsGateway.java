package com.courseproject.tindar.usecases.signup;

public interface SignUpDsGateway {
    boolean checkIfEmailAlreadyUsed(String email);
    String addAccount(SignUpDsRequestModel signUpDsRequestModel);
}
