package com.ashishbagdane.lib.core.db.entity.approval;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Base implementation of Approvable interface.
 */
@Getter
public abstract class AbstractApprovableEntity implements Approvable {

    private ApprovalStatus approvalStatus = ApprovalStatus.PENDING_APPROVAL;

    private LocalDateTime approvedAt;

    private String approvedBy;

    private LocalDateTime rejectedAt;

    private String rejectedBy;

    private String rejectionReason;

    private LocalDateTime revokedAt;

    private String revokedBy;

    private String revocationReason;

    @Override
    public void submitForApproval(String submittedBy) {
        validateStateTransition(ApprovalStatus.IN_REVIEW);
        this.approvalStatus = ApprovalStatus.IN_REVIEW;
        onSubmitForApproval(submittedBy);
    }

    @Override
    public void approve(String approvedBy) {
        validateStateTransition(ApprovalStatus.APPROVED);
        this.approvalStatus = ApprovalStatus.APPROVED;
        this.approvedAt = LocalDateTime.now();
        this.approvedBy = approvedBy;
        clearRejectionDetails();
        onApprove();
    }

    @Override
    public void reject(String rejectedBy, String reason) {
        validateStateTransition(ApprovalStatus.REJECTED);
        this.approvalStatus = ApprovalStatus.REJECTED;
        this.rejectedAt = LocalDateTime.now();
        this.rejectedBy = rejectedBy;
        this.rejectionReason = reason;
        clearApprovalDetails();
        onReject();
    }

    @Override
    public void revoke(String revokedBy, String reason) {
        validateStateTransition(ApprovalStatus.REVOKED);
        this.approvalStatus = ApprovalStatus.REVOKED;
        this.revokedAt = LocalDateTime.now();
        this.revokedBy = revokedBy;
        this.revocationReason = reason;
        onRevoke();
    }

    @Override
    public Optional<LocalDateTime> getApprovedAt() {
        return Optional.ofNullable(approvedAt);
    }

    @Override
    public Optional<String> getApprovedBy() {
        return Optional.ofNullable(approvedBy);
    }

    @Override
    public Optional<String> getRejectionReason() {
        return Optional.ofNullable(rejectionReason);
    }

    @Override
    public Optional<String> getRejectedBy() {
        return Optional.ofNullable(rejectedBy);
    }

    @Override
    public Optional<LocalDateTime> getRejectedAt() {
        return Optional.ofNullable(rejectedAt);
    }

    private void validateStateTransition(ApprovalStatus targetStatus) {
        if (!approvalStatus.canTransitionTo(targetStatus)) {
            throw new IllegalApprovalStateException(
                String.format("Cannot transition from %s to %s", approvalStatus, targetStatus)
            );
        }
    }

    private void clearApprovalDetails() {
        this.approvedAt = null;
        this.approvedBy = null;
    }

    private void clearRejectionDetails() {
        this.rejectedAt = null;
        this.rejectedBy = null;
        this.rejectionReason = null;
    }

    // Hook methods for custom behavior
    protected void onSubmitForApproval(String submittedBy) {}

    protected void onApprove() {}

    protected void onReject() {}

    protected void onRevoke() {}
}
