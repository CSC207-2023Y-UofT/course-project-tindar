package com.courseproject.tindar.usecases.signup;

import com.courseproject.tindar.entities.Account;
import com.courseproject.tindar.entities.AccountFactory;
import com.courseproject.tindar.presenters.signup.InvalidCredentials;

import java.util.Objects;

public class SignUpInteractor implements SignUpInputBoundary {
    private final SignUpDsGateway signUpDsGateway;
    private final SignUpPresenter signUpPresenter;
    private final AccountFactory accountFactory;

    public SignUpInteractor(SignUpDsGateway signUpDsGateway, SignUpPresenter signUpPresenter,
                            AccountFactory accountFactory) {
        this.signUpDsGateway = signUpDsGateway;
        this.signUpPresenter = signUpPresenter;
        this.accountFactory = accountFactory;
    }

    @Override
    public String createAccount(SignUpRequestModel accountCredentials) throws InvalidCredentials {
        if (!Objects.equals(accountCredentials.getPassword(), accountCredentials.getRetypedPassword())) {
            return signUpPresenter.prepareFailView("Password and re-typed password do not match.");
        }
        Account account = accountFactory.create(accountCredentials.getPassword());
        if (!account.checkIfPasswordIsValid()) {
            return signUpPresenter.prepareFailView(
                    "Invalid password. The password has to be at least 6 characters long.");
        }

        if (signUpDsGateway.checkIfEmailAlreadyUsed(accountCredentials.getEmail())) {
            return signUpPresenter.prepareFailView("Email is already in use.");
        }

        String userId = signUpDsGateway.addAccount(new SignUpDsRequestModel(accountCredentials.getDisplayName(),
                accountCredentials.getEmail(), accountCredentials.getPassword()));

        if (Objects.equals(userId, "-1")) {
            return signUpPresenter.prepareFailView("Unknown error. Fail to create an account. Please try it again.");
        }

        return signUpPresenter.prepareSuccessView();
    }
}
