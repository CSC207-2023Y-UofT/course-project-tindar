package com.courseproject.tindar.controllers.editaccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.courseproject.tindar.usecases.editaccount.EditAccountDsResponseModel;
import com.courseproject.tindar.usecases.editaccount.EditAccountInputBoundary;

import org.junit.Test;

public class EditAccountControllerUnitTest {
    private static final String USER_ID = "3";
    private static final boolean IS_ACTIVE_STATUS = true;
    private static final String EMAIL = "johndoe@email.com";
    private static final String PASSWORD = "J0HND03";

    EditAccountDsResponseModel mockEditAccountResponseModel =
            new EditAccountDsResponseModel(IS_ACTIVE_STATUS, EMAIL, PASSWORD);

    private class MockEditAccountUserInput implements EditAccountInputBoundary {
        public EditAccountDsResponseModel getAccount(String userId) {
            return mockEditAccountResponseModel;
        }

        @Override
        public void updateIsActiveStatus(String userId, boolean isActiveStatus) {
            assertEquals(USER_ID, userId);
            assertEquals(IS_ACTIVE_STATUS, isActiveStatus);
        }

        @Override
        public boolean updateEmail(String userId, String email) {
            try {
                assertEquals(USER_ID, userId);
            }
            catch (AssertionError e){
                return false;
            }
            try {
                assertEquals(EMAIL, email);
            }
            catch (AssertionError e){
                return false;
            }
            return true;
        }

        @Override
        public boolean updatePassword(String userId, String password) {
            try {
                assertEquals(USER_ID, userId);
            }
            catch (AssertionError e){
                return false;
            }
            try {
                assertEquals(PASSWORD, password);
            }
            catch (AssertionError e){
                return false;
            }
            return true;
        }
    }

    EditAccountInputBoundary mockEditAccountUserInput = new MockEditAccountUserInput();

    @Test
    public void getAccount() {
        EditAccountController testEditAccountController = new EditAccountController(mockEditAccountUserInput);
        EditAccountDsResponseModel testEditAccountDsResponseModel = testEditAccountController.getAccount(USER_ID);
        assertEquals(IS_ACTIVE_STATUS, testEditAccountDsResponseModel.getIsActiveStatus());
        assertEquals(EMAIL, testEditAccountDsResponseModel.getEmail());
        assertEquals(PASSWORD, testEditAccountDsResponseModel.getPassword());
    }

    @Test
    public void validatePassword() {
        EditAccountController testEditAccountController = new EditAccountController(mockEditAccountUserInput);
        assertTrue(testEditAccountController.validatePassword(USER_ID, PASSWORD));
    }

    @Test
    public void updateIsActiveStatus() {
        EditAccountController testEditAccountController = new EditAccountController(mockEditAccountUserInput);
        testEditAccountController.updateIsActiveStatus(USER_ID, IS_ACTIVE_STATUS);
    }

    @Test
    public void updateEmail() {
        EditAccountController testEditAccountController = new EditAccountController(mockEditAccountUserInput);
        assertTrue(testEditAccountController.updateEmail(USER_ID, EMAIL));
    }

    @Test
    public void updatePassword() {
        EditAccountController testEditAccountController = new EditAccountController(mockEditAccountUserInput);
        assertTrue(testEditAccountController.updatePassword(USER_ID, PASSWORD));
    }

}
