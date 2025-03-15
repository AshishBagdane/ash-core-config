package com.ashishbagdane.lib.core.db.entity.status;

import java.time.LocalDateTime;

/**
 * Interface for entities that need status tracking functionality. Provides methods to manage and audit status changes.
 */
public interface Trackable {

    /**
     * Get the current status of the entity.
     *
     * @return current EntityStatus
     */
    EntityStatus getStatus();

    /**
     * Get the timestamp of the last status change.
     *
     * @return timestamp of last status change
     */
    LocalDateTime getStatusChangeDate();

    /**
     * Get the user who last changed the status.
     *
     * @return username of last status changer
     */
    String getStatusChangedBy();

    /**
     * Update the status of the entity.
     *
     * @param newStatus the new status to set
     * @param changedBy the user making the change
     * @throws IllegalStatusTransitionException if transition is not allowed
     */
    void updateStatus(EntityStatus newStatus, String changedBy);
}
