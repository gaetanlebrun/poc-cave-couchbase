package com.oxiane.caveavin.exception;

public class DocMappingException extends RuntimeException {
  public DocMappingException(Exception e) {
    super(e);
  }

  public DocMappingException(String message) {
    super(message);
  }

  public DocMappingException(String message, Exception e) {
    super(message, e);
  }
}
