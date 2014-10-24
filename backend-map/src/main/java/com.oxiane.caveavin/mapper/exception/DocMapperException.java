package com.oxiane.caveavin.mapper.exception;

public class DocMapperException extends RuntimeException {
  // --------------------------- CONSTRUCTORS ---------------------------

  public DocMapperException(Exception e) {
    super(e);
  }

  public DocMapperException(String message) {
    super(message);
  }

  public DocMapperException(String message, Exception e) {
    super(message, e);
  }
}
