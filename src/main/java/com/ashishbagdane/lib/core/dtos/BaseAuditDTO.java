package com.ashishbagdane.lib.core.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ashishbagdane.lib.core.db.entity.BaseAuditEntity;
import com.ashishbagdane.lib.core.enums.HttpMethod;
import com.ashishbagdane.lib.core.enums.OperationType;
import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Base Record class for Data Transfer Objects that correspond to entities extending BaseAuditEntity. This record provides a standard structure for audit-related data in API responses.
 *
 * <h2>Features:</h2>
 * <ul>
 *     <li>Immutable data structure using Java Record</li>
 *     <li>JSON serialization configuration</li>
 *     <li>Proper date-time formatting</li>
 *     <li>Null handling</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 * {@code
 * public record ProductDTO(
 *     String name,
 *     BigDecimal price,
 *     // Additional fields specific to Product
 * ) extends BaseAuditDTO {
 *     // Constructor calling super with audit fields
 *     public ProductDTO(UUID id, Long version, ..., String name, BigDecimal price) {
 *         super(id, version, ...);
 *         this.name = name;
 *         this.price = price;
 *     }
 * }
 * }
 * </pre>
 *
 * @param id                 Unique identifier
 * @param version            Optimistic locking version
 * @param endpointPath       The API endpoint path
 * @param userPrincipal      The user who performed the operation
 * @param ipAddress          IP address of the request
 * @param userAgent          User agent of the request
 * @param systemOperation    Indicates if operation was performed by system
 * @param operationType      Type of operation (CREATE, UPDATE, DELETE)
 * @param httpMethod         HTTP method used
 * @param operationTimestamp Timestamp of the operation
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public record BaseAuditDTO(
    UUID id,
    Long version,
    String endpointPath,
    String userPrincipal,
    String ipAddress,
    String userAgent,
    boolean systemOperation,
    OperationType operationType,
    HttpMethod httpMethod,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    ZonedDateTime operationTimestamp
) {

  /**
   * Static factory method to create a DTO from an entity
   *
   * @param entity The BaseAuditEntity instance
   * @return A new BaseAuditDTO instance
   */
  public static BaseAuditDTO fromEntity(BaseAuditEntity entity) {
    return new BaseAuditDTO(
        entity.getId(),
        entity.getVersion(),
        entity.getEndpointPath(),
        entity.getUserContext().getPrincipalName(),
        entity.getUserContext().getIpAddress(),
        entity.getUserContext().getUserAgent(),
        entity.getUserContext().isSystemOperation(),
        entity.getOperationType(),
        entity.getHttpMethod(),
        entity.getOperationTimestamp()
    );
  }

  /**
   * Creates a new BaseAuditDTO with updated version. Useful when returning response after an update operation.
   *
   * @param newVersion The new version number
   * @return A new BaseAuditDTO instance with updated version
   */
  public BaseAuditDTO withVersion(Long newVersion) {
    return new BaseAuditDTO(
        this.id,
        newVersion,
        this.endpointPath,
        this.userPrincipal,
        this.ipAddress,
        this.userAgent,
        this.systemOperation,
        this.operationType,
        this.httpMethod,
        this.operationTimestamp
    );
  }

  /**
   * Creates a new BaseAuditDTO with updated operation type.
   *
   * @param newOperationType The new operation type
   * @return A new BaseAuditDTO instance with updated operation type
   */
  public BaseAuditDTO withOperationType(OperationType newOperationType) {
    return new BaseAuditDTO(
        this.id,
        this.version,
        this.endpointPath,
        this.userPrincipal,
        this.ipAddress,
        this.userAgent,
        this.systemOperation,
        newOperationType,
        this.httpMethod,
        this.operationTimestamp
    );
  }
}
