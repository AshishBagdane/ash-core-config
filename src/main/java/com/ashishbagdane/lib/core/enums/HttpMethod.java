package com.ashishbagdane.lib.core.enums;

/**
 * Enumeration of standard HTTP methods as defined in RFC 7231 and RFC 5789. Each method has an associated numeric value for potential ordering or mapping purposes.
 */
public enum HttpMethod {
  /**
   * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data and should have no other effect.
   */
  GET(1),

  /**
   * The POST method submits an entity to the specified resource, often causing a change in state or side effects on the server.
   */
  POST(2),

  /**
   * The PUT method replaces all current representations of the target resource with the request payload.
   */
  PUT(3),

  /**
   * The DELETE method deletes the specified resource.
   */
  DELETE(4),

  /**
   * The PATCH method applies partial modifications to a resource.
   */
  PATCH(5),

  /**
   * The HEAD method asks for a response identical to a GET request, but without the response body.
   */
  HEAD(6),

  /**
   * The OPTIONS method describes the communication options for the target resource.
   */
  OPTIONS(7),

  /**
   * The TRACE method performs a message loop-back test along the path to the target resource.
   */
  TRACE(8);

  private final int value;

  /**
   * Constructor for HttpMethod enum.
   *
   * @param value numeric value associated with the HTTP method
   */
  HttpMethod(int value) {
    this.value = value;
  }

  /**
   * Gets the numeric value associated with this HTTP method.
   *
   * @return the numeric value of the HTTP method
   */
  public int getValue() {
    return value;
  }
}
