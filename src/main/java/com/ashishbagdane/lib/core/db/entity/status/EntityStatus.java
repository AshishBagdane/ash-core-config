package com.ashishbagdane.lib.core.db.entity.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

/**
 * Represents the common status states for entities. Each status includes metadata about its properties and allowed
 * transitions.
 */
@Getter
@RequiredArgsConstructor
public enum EntityStatus {
    DRAFT(false, false, "Initial draft state"),
    PENDING(false, false, "Awaiting action or review"),
    ACTIVE(true, false, "Currently active and valid"),
    SUSPENDED(false, false, "Temporarily inactive"),
    ARCHIVED(false, true, "No longer active but preserved"),
    DELETED(false, true, "Marked for deletion");

    private final boolean active;

    private final boolean finalState;

    private final String description;

    /**
     * Checks if transition to target status is allowed.
     *
     * @param targetStatus the status to transition to
     * @return true if transition is allowed
     */
    public boolean canTransitionTo(EntityStatus targetStatus) {
        if (this == targetStatus) {
            return false;
        }
        if (this.finalState) {
            return false;
        }

        return switch (this) {
            case DRAFT -> Set.of(PENDING, DELETED).contains(targetStatus);
            case PENDING -> Set.of(ACTIVE, DRAFT, DELETED).contains(targetStatus);
            case ACTIVE -> Set.of(SUSPENDED, ARCHIVED, DELETED).contains(targetStatus);
            case SUSPENDED -> Set.of(ACTIVE, ARCHIVED, DELETED).contains(targetStatus);
            case ARCHIVED, DELETED -> false;
        };
    }
}
