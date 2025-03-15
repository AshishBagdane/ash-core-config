package com.ashishbagdane.lib.core.db.entity.approval;

/**
 * Exception thrown when an invalid approval state transition is attempted.
 */
public class IllegalApprovalStateException extends RuntimeException {

    public IllegalApprovalStateException(String message) {
        super(message);
    }
}
