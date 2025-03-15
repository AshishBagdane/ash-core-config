package com.ashishbagdane.lib.core.db.entity.status;

import java.time.LocalDateTime;

/**
 * Base implementation of Trackable interface. Provides common status management functionality.
 */
public abstract class AbstractTrackableEntity implements Trackable {

    private EntityStatus status = EntityStatus.DRAFT;

    private LocalDateTime statusChangeDate;

    private String statusChangedBy;

    @Override
    public void updateStatus(EntityStatus newStatus, String changedBy) {
        if (!status.canTransitionTo(newStatus)) {
            throw new IllegalStatusTransitionException(
                String.format("Cannot transition from %s to %s", status, newStatus)
            );
        }

        this.status = newStatus;
        this.statusChangeDate = LocalDateTime.now();
        this.statusChangedBy = changedBy;

        onStatusChange(newStatus);
    }

    /**
     * Hook method called after status change. Override to add custom behavior.
     *
     * @param newStatus the new status
     */
    protected void onStatusChange(EntityStatus newStatus) {
        // Hook method for subclasses
    }

    @Override
    public EntityStatus getStatus() {
        return status;
    }

    @Override
    public LocalDateTime getStatusChangeDate() {
        return statusChangeDate;
    }

    @Override
    public String getStatusChangedBy() {
        return statusChangedBy;
    }
}
