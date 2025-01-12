package dev.ash.core.lib.dtos;

/**
 * A generic record for representing enum values in a standardized format. This record is particularly useful when transferring enum data across service boundaries or when providing enum values
 * through APIs.
 *
 * @param id    The numeric identifier of the enum value
 * @param value The string representation of the enum value
 * @author Your Name
 * @version 1.0
 * @since 1.0
 */
public record EnumDTO(Integer id, String value) {

  /**
   * Validates that neither id nor value is null.
   *
   * @throws IllegalArgumentException if either id or value is null
   */
  public EnumDTO {
    if (id == null) {
      throw new IllegalArgumentException("id cannot be null");
    }
    if (value == null) {
      throw new IllegalArgumentException("value cannot be null");
    }
  }

  /**
   * Creates an EnumDTO from any enum that implements the dev.ash.core.lib.dtos.EnumMappable interface.
   *
   * @param enumValue the enum value to convert
   * @param <E>       the type of the enum
   * @return a new EnumDTO representing the enum value
   * @throws IllegalArgumentException if the enum value is null
   */
  public static <E extends Enum<E> & EnumMappable> EnumDTO fromEnum(E enumValue) {
    if (enumValue == null) {
      throw new IllegalArgumentException("enumValue cannot be null");
    }
    return new EnumDTO(enumValue.getId(), enumValue.name());
  }
}