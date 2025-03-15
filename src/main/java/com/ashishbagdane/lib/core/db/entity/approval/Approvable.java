package com.ashishbagdane.lib.core.db.entity.approval;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Interface for entities that require approval workflow.
 */
public interface Approvable {

    /**
     * Get current approval status.
     */
    ApprovalStatus getApprovalStatus();

    /**
     * Get the timestamp when the entity was last approved.
     */
    Optional<LocalDateTime> getApprovedAt();

    /**
     * Get the user who approved the entity.
     */
    Optional<String> getApprovedBy();

    /**
     * Get rejection reason if entity was rejected.
     */
    Optional<String> getRejectionReason();

    /**
     * Get the user who rejected the entity.
     */
    Optional<String> getRejectedBy();

    /**
     * Get the timestamp when the entity was rejected.
     */
    Optional<LocalDateTime> getRejectedAt();

    /**
     * Submit for approval.
     *
     * @param submittedBy User submitting for approval
     */
    void submitForApproval(String submittedBy);

    /**
     * Approve the entity.
     *
     * @param approvedBy User approving the entity
     * @throws IllegalApprovalStateException if entity cannot be approved
     */
    void approve(String approvedBy);

    /**
     * Reject the entity with a reason.
     *
     * @param rejectedBy User rejecting the entity
     * @param reason     Reason for rejection
     * @throws IllegalApprovalStateException if entity cannot be rejected
     */
    void reject(String rejectedBy, String reason);

    /**
     * Revoke a previously approved entity.
     *
     * @param revokedBy User revoking the approval
     * @param reason    Reason for revocation
     * @throws IllegalApprovalStateException if entity cannot be revoked
     */
    void revoke(String revokedBy, String reason);
}
