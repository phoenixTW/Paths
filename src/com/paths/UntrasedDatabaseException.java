package com.paths;

/**
 * Created by kaustavc on 3/10/2015.
 * An Exception class for untracked File.
 */
public class UntrasedDatabaseException extends Exception {

    public String message;

    UntrasedDatabaseException(String e) {
        this.message = "No database named \"" + e + "\" found.";
    }

    @Override
    public String getLocalizedMessage() {
        return this.message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
