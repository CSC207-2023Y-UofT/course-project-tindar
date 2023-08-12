package com.courseproject.tindar.usecases.signup;

import com.courseproject.tindar.entities.Account;
import com.courseproject.tindar.entities.AccountFactory;
import com.courseproject.tindar.presenters.signup.InvalidSignUpCredentials;

import java.util.Objects;

/**
 * interactor of the Sign Up feature
 */
public class SignUpInteractor implements SignUpInputBoundary {
    /**
     * data-saving gateway of Sign Up feature
     */
    private final SignUpDsGateway signUpDsGateway;
    /**
     * presenter of the Sign Up feature
     */
    private final SignUpPresenter signUpPresenter;
    /**
     * factory to create Account entity
     */
    private final AccountFactory accountFactory;

    /**
     * constructs interactor of the Sign Up feature
     *
     * @param signUpDsGateway data-saving gateway of Sign Up feature
     * @param signUpPresenter presenter of the Sign Up feature
     * @param accountFactory factory to create Account entity
     */
    public SignUpInteractor(SignUpDsGateway signUpDsGateway, SignUpPresenter signUpPresenter,
                            AccountFactory accountFactory) {
        this.signUpDsGateway = signUpDsGateway;
        this.signUpPresenter = signUpPresenter;
        this.accountFactory = accountFactory;
    }

    /**
     * creates a new account with the provided sign-up credentials
     *
     * @param signUpCredentials provided sign-up credentials. The credentials include display name, email, password
     *                          and retyped password
     * @return success or fail message on account creation
     * @throws InvalidSignUpCredentials if the provided sign-up credentials is invalid. i.e. password confirmation
     * does not match, provided password is invalid, and provided email is already used by another user
     */
    @Override
    public String createAccount(SignUpRequestModel signUpCredentials) throws InvalidSignUpCredentials {
        if (!Objects.equals(signUpCredentials.getPassword(), signUpCredentials.getRetypedPassword())) {
            return signUpPresenter.prepareFailView("Password and re-typed password do not match.");
        }
        Account account = accountFactory.create(signUpCredentials.getPassword());
        if (!account.checkIfPasswordIsValid()) {
            return signUpPresenter.prepareFailView(
                    "Invalid password. The password has to be at least 6 characters long.");
        }

        if (signUpDsGateway.checkIfEmailAlreadyUsed(signUpCredentials.getEmail())) {
            return signUpPresenter.prepareFailView("Email is already in use.");
        }

        String userId = signUpDsGateway.addAccount(new SignUpDsRequestModel(signUpCredentials.getDisplayName(),
                signUpCredentials.getEmail(), signUpCredentials.getPassword()));

        if (Objects.equals(userId, "-1")) {
            return signUpPresenter.prepareFailView("Unknown error. Fail to create an account. Please try it again.");
        }

        return signUpPresenter.prepareSuccessView("Account has successfully created.");
    }
}
