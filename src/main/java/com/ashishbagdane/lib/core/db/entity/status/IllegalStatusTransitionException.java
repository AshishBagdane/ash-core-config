package com.ashishbagdane.lib.core.db.entity.status;

/**
 * Exception thrown when an invalid status transition is attempted.
 */
public class IllegalStatusTransitionException extends RuntimeException {

    public IllegalStatusTransitionException(String message) {
        super(message);
    }
}
