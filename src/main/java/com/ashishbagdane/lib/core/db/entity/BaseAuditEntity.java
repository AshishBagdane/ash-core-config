package com.ashishbagdane.lib.core.db.entity;

import com.ashishbagdane.lib.core.enums.HttpMethod;
import com.ashishbagdane.lib.core.enums.OperationType;
import com.ashishbagdane.lib.core.model.UserContext;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Version;
import java.time.ZonedDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * Base entity class that provides auditing functionality for all domain entities. This abstract class implements common auditing fields and behaviors that should be inherited by all entities
 * requiring operation tracking.
 *
 * <h2>Features:</h2>
 * <ul>
 *     <li>Automatic UUID generation</li>
 *     <li>Optimistic locking using version field</li>
 *     <li>Operation tracking (CREATE, UPDATE, DELETE)</li>
 *     <li>User context tracking (user details, IP, user agent)</li>
 *     <li>Automatic timestamp management</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * @Entity
 * @Table(name = "products")
 * public class Product extends BaseAuditEntity {
 *     private String name;
 *     private BigDecimal price;
 *
 *     // Additional fields and methods
 * }
 * }
 * </pre>
 *
 * <h2>Important Implementation Notes:</h2>
 * <ul>
 *     <li>The class uses TABLE_PER_CLASS inheritance strategy, meaning each entity will have all audit fields in its own table</li>
 *     <li>Timestamp is automatically updated on all operations through JPA callbacks</li>
 *     <li>Operation type is automatically set based on the performed operation</li>
 *     <li>User context should be set before persisting/updating the entity</li>
 * </ul>
 *
 * <h2>Best Practices:</h2>
 * <ol>
 *     <li>Always call super() in child entity constructors</li>
 *     <li>Set user context before saving:
 *         <pre>
 *         {@code
 *         entity.setUserContext(UserContext.createUserContext(
 *             principal.getName(),
 *             request.getRemoteAddr(),
 *             request.getHeader("User-Agent")
 *         ));
 *         }
 *         </pre>
 *     </li>
 *     <li>Set endpoint path in your controller or service layer:
 *         <pre>
 *         {@code
 *         entity.setEndpointPath(request.getRequestURI());
 *         entity.setHttpMethod(HttpMethod.valueOf(request.getMethod()));
 *         }
 *         </pre>
 *     </li>
 * </ol>
 *
 * <h2>Database Considerations:</h2>
 * <ul>
 *     <li>UUID is used as primary key for better distribution in distributed systems</li>
 *     <li>Version column handles optimistic locking to prevent concurrent modifications</li>
 *     <li>Timestamps are stored with timezone information</li>
 * </ul>
 *
 * @see UserContext
 * @see OperationType
 * @see HttpMethod
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
public abstract class BaseAuditEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator"
  )
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Version
  @Column(name = "version")
  private Long version;

  @Column(name = "endpoint_path", length = 255)
  private String endpointPath;

  @Embedded
  private UserContext userContext;

  @Enumerated(EnumType.STRING)
  @Column(name = "operation_type", nullable = false)
  private OperationType operationType;

  @Enumerated(EnumType.STRING)
  @Column(name = "http_method", nullable = false)
  private HttpMethod httpMethod;

  @Column(name = "operation_timestamp", nullable = false)
  private ZonedDateTime operationTimestamp;

  /**
   * Lifecycle callback that is executed before the entity is persisted. Sets the initial operation timestamp and marks the operation type as CREATE.
   */
  @PrePersist
  protected void onCreate() {
    operationTimestamp = ZonedDateTime.now();
    operationType = OperationType.CREATE;
  }

  /**
   * Lifecycle callback that is executed before the entity is updated. Updates the operation timestamp and marks the operation type as UPDATE.
   */
  @PreUpdate
  protected void onUpdate() {
    operationTimestamp = ZonedDateTime.now();
    operationType = OperationType.UPDATE;
  }

  /**
   * Lifecycle callback that is executed before the entity is deleted. Updates the operation timestamp and marks the operation type as DELETE.
   */
  @PreRemove
  protected void onDelete() {
    operationTimestamp = ZonedDateTime.now();
    operationType = OperationType.DELETE;
  }

  /**
   * Default constructor for BaseAuditEntity. Required by JPA for entity instantiation and inheritance support. This constructor is protected to ensure that this class can only be extended and not
   * instantiated directly.
   *
   * <p>The constructor initializes an empty entity with null values for all fields.
   * Fields will be populated through setters or JPA lifecycle callbacks.</p>
   *
   * <p>Note: When extending this class, always call super() in the child class constructor:</p>
   * <pre>
   * public class ChildEntity extends BaseAuditEntity {
   *     public ChildEntity() {
   *         super(); // Required call to parent constructor
   *     }
   * }
   * </pre>
   */
  protected BaseAuditEntity() {
    // Default constructor required by JPA
  }
}
