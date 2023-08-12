package com.courseproject.tindar.presenters.editfilters;

/**
 * invalid age group exception. Subclass of Exception
 */
public class InvalidAgeGroup extends Exception {
    /**
     * constructs invalid age group exception
     *
     * @param error error message
     */
    public InvalidAgeGroup(String error) {
        super(error);
    }
}
