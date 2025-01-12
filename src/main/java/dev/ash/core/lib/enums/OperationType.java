package dev.ash.core.lib.enums;

/**
 * Represents the type of database operation being performed on an entity. This enum is used in conjunction with audit logging to track changes to entities.
 */
public enum OperationType {
  /**
   * Represents the creation of a new entity in the system. This operation type is automatically set during the {@code @PrePersist} phase.
   */
  CREATE,
  /**
   * Represents the modification of an existing entity in the system. This operation type is automatically set during the {@code @PreUpdate} phase.
   */
  UPDATE,
  /**
   * Represents the removal of an existing entity from the system. This operation type is automatically set during the {@code @PreRemove} phase.
   */
  DELETE
}