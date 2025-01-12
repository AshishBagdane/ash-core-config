package dev.ash.core.lib.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Embeddable value object to store user context information for auditing purposes. This class captures authentication and request details that are relevant for tracking who performed an operation and
 * from where.
 *
 * <p>This class uses Lombok's {@code @Builder} pattern for object creation.
 * Example usage:
 * <pre>
 * UserContext context = UserContext.builder()
 *     .principalName("john.doe")
 *     .ipAddress("192.168.1.1")
 *     .userAgent("Mozilla/5.0")
 *     .build();
 * </pre>
 *
 * <p>This class provides both a no-args constructor (required for JPA) and
 * a builder pattern for convenient object creation. The no-args constructor creates an empty instance which should be populated using setters or builder methods.
 *
 * @see #createSystemContext()
 * @see #createUserContext(String, String, String)
 */
@Embeddable
@Data
@Builder
@NoArgsConstructor(force = true)  // Required for JPA
@AllArgsConstructor
public class UserContext {

  /**
   * The username or identifier of the principal (user) performing the operation. Maximum length is 50 characters.
   */
  @Column(name = "principal_name", length = 50)
  private String principalName;

  /**
   * The IP address from which the request originated. Length of 45 characters to accommodate IPv6 addresses.
   */
  @Column(name = "ip_address", length = 45)
  private String ipAddress;

  /**
   * The user agent string identifying the client application or browser. Maximum length is 255 characters.
   */
  @Column(name = "user_agent", length = 255)
  private String userAgent;

  /**
   * Flag indicating whether the operation was performed by the system rather than a human user.
   */
  @Column(name = "is_system_operation")
  private boolean systemOperation;

  /**
   * Creates a UserContext instance for system operations. This factory method sets up a context with system-specific values.
   *
   * @return a new UserContext instance configured for system operations
   */
  public static UserContext createSystemContext() {
    return UserContext.builder()
        .principalName("SYSTEM")
        .systemOperation(true)
        .build();
  }

  /**
   * Creates a UserContext instance for user operations. This factory method sets up a context with the provided user details.
   *
   * @param principalName the name of the authenticated user
   * @param ipAddress     the IP address of the request
   * @param userAgent     the user agent string from the request
   * @return a new UserContext instance configured for user operations
   */
  public static UserContext createUserContext(String principalName, String ipAddress, String userAgent) {
    return UserContext.builder()
        .principalName(principalName)
        .ipAddress(ipAddress)
        .userAgent(userAgent)
        .systemOperation(false)
        .build();
  }
}