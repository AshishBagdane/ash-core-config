package com.ashishbagdane.lib.core.dtos;

/**
 * Interface to be implemented by enums that can be converted to EnumDTO. This interface ensures that any enum that should be convertible to EnumDTO provides the necessary getId method.
 */
public interface EnumMappable {

  /**
   * Gets the numeric identifier for the enum value.
   *
   * @return the id associated with this enum value
   */
  Integer getId();
}
