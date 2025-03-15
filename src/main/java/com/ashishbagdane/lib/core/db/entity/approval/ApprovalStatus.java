package com.ashishbagdane.lib.core.db.entity.approval;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Objects;
import java.util.Set;

/**
 * Represents the possible approval states for an entity.
 */
@Getter
@RequiredArgsConstructor
public enum ApprovalStatus {
    PENDING_APPROVAL("Awaiting initial approval", false),
    IN_REVIEW("Under review process", false),
    APPROVED("Approved and valid", true),
    REJECTED("Rejected with comments", true),
    REVOKED("Previously approved, now revoked", true);

    private final String description;

    private final boolean finalState;

    public boolean canTransitionTo(ApprovalStatus targetStatus) {
        if (this == targetStatus) {
            return false;
        }
        if (this.finalState && targetStatus != REVOKED) {
            return false;
        }

        return switch (this) {
            case PENDING_APPROVAL -> Set.of(IN_REVIEW, REJECTED).contains(targetStatus);
            case IN_REVIEW -> Set.of(APPROVED, REJECTED).contains(targetStatus);
            case APPROVED -> Objects.equals(REVOKED, targetStatus);
            case REJECTED, REVOKED -> false;
        };
    }
}
