package com.ashishbagdane.lib.core.dtos.example;

import com.ashishbagdane.lib.core.dtos.EnumDTO;
import com.ashishbagdane.lib.core.dtos.EnumMappable;

/**
 * Represents the various states an entity can be in within the system. This enum implements {@link EnumMappable} to allow for standardized conversion to {@link EnumDTO} format.
 *
 * <p>Each status has an associated numeric ID that is used for database storage
 * and API communications.</p>
 *
 * <p><b>Usage Examples:</b></p>
 * <pre>
 * // Basic usage
 * Status status = Status.ACTIVE;
 * Integer id = status.getId(); // Returns 1
 *
 * // Converting to EnumDTO
 * EnumDTO dto = EnumDTO.fromEnum(Status.ACTIVE);
 *
 * // Usage in JPA Entity
 * &#64;Entity
 * public class User {
 *     &#64;Enumerated(EnumType.STRING)
 *     private Status status = Status.PENDING;
 * }
 *
 * // Usage in REST Controller
 * &#64;GetMapping("/status/{id}")
 * public EnumDTO getStatus(@PathVariable Integer id) {
 *     Status status = Arrays.stream(Status.values())
 *             .filter(s -> s.getId().equals(id))
 *             .findFirst()
 *             .orElseThrow(() -> new IllegalArgumentException("Invalid status ID"));
 *     return EnumDTO.fromEnum(status);
 * }
 *
 * // Usage with Stream API
 * List&lt;{@link EnumDTO}&gt; allStatuses = Arrays.stream(Status.values())
 *         .map({@link EnumDTO}::fromEnum)
 *         .collect(Collectors.toList());
 * </pre>
 *
 * @see EnumMappable
 * @see EnumDTO
 * @since 1.0
 */
public enum Status implements EnumMappable {
  /**
   * Indicates that the entity is currently active and operational. Active entities are fully functional and available for normal operations.
   */
  ACTIVE(1),
  /**
   * Indicates that the entity is currently not active or disabled. Inactive entities are not available for normal operations but retain their data.
   */
  INACTIVE(2),
  /**
   * Indicates that the entity is awaiting some action or approval. Pending entities are in a transitional state before becoming active or inactive.
   */
  PENDING(3);

  private final Integer id;

  Status(Integer id) {
    this.id = id;
  }

  @Override
  public Integer getId() {
    return id;
  }
}
