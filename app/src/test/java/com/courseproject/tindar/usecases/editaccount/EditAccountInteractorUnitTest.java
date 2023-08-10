package com.courseproject.tindar.usecases.editaccount;

import org.junit.Test;

import static org.junit.Assert.*;

public class EditAccountInteractorUnitTest {
    private final String USER_ID = "3";
    private final boolean IS_ACTIVE_STATUS = true;
    private final String EMAIL = "johndoe@email.com";
    private final String PASSWORD = "goodpassword";

    EditAccountDsResponseModel mockEditAccountResponseModel =
            new EditAccountDsResponseModel(IS_ACTIVE_STATUS, EMAIL, PASSWORD);

    private class MockEditAccountDsGateway implements EditAccountDsGateway {
        @Override
        public EditAccountDsResponseModel readAccount(String userId) {
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

    EditAccountDsGateway mockEditAccountDsGateway = new MockEditAccountDsGateway();

    @Test
    public void getAccount() {
        EditAccountInteractor testEditAccountInteractor = new EditAccountInteractor(mockEditAccountDsGateway);
        EditAccountDsResponseModel testEditAccountDsResponseModel = testEditAccountInteractor.getAccount(USER_ID);
        assertEquals(IS_ACTIVE_STATUS, testEditAccountDsResponseModel.getIsActiveStatus());
        assertEquals(EMAIL, testEditAccountDsResponseModel.getEmail());
        assertEquals(PASSWORD, testEditAccountDsResponseModel.getPassword());
    }

    @Test
    public void updateIsActiveStatus() {
        EditAccountInteractor testEditAccountInteractor = new EditAccountInteractor(mockEditAccountDsGateway);
        testEditAccountInteractor.updateIsActiveStatus(USER_ID, IS_ACTIVE_STATUS);
    }

    @Test
    public void updateEmail() {
        EditAccountInteractor testEditAccountInteractor = new EditAccountInteractor(mockEditAccountDsGateway);
        assertTrue(testEditAccountInteractor.updateEmail(USER_ID, EMAIL));
    }

    @Test
    public void updatePassword() {
        EditAccountInteractor testEditAccountInteractor = new EditAccountInteractor(mockEditAccountDsGateway);
        assertTrue(testEditAccountInteractor.updatePassword(USER_ID, PASSWORD));
    }

    @Test
    public void updatePasswordEmpty() {
        EditAccountInteractor testEditAccountInteractor = new EditAccountInteractor(mockEditAccountDsGateway);
        assertFalse(testEditAccountInteractor.updatePassword(USER_ID, ""));
    }



}
