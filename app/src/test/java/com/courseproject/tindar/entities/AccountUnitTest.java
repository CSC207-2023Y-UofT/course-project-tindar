package com.courseproject.tindar.entities;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AccountUnitTest {
    @Test
    public void testCheckIfPasswordIsValidWithValidPassword() {
        Account account = new Account("abcdefg");
        assertTrue(account.checkIfPasswordIsValid());
    }

    @Test
    public void testCheckIfPasswordIsValidWithShortPassword() {
        Account account = new Account("abcde");
        assertFalse(account.checkIfPasswordIsValid());
    }
}
