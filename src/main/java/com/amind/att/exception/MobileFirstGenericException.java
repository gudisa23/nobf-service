
package com.amind.att.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * @author medea
 */
public class MobileFirstGenericException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  private final long timeStamp = System.currentTimeMillis();

  /**
   * Mobile First Generic Exception Constructor
   */
  public MobileFirstGenericException() {
    super();
  }

  /**
   * Mobile First Generic Exception Constructor
   *
   * @param message
   */
  public MobileFirstGenericException(String message) {
    super(message);
  }

  /**
   * Mobile First Generic Exception Constructor
   *
   * @param cause
   */
  public MobileFirstGenericException(Throwable cause) {
    super(cause);
  }

  /**
   * Mobile First Generic Exception Constructor
   *
   * @param message
   * @param cause
   */
  public MobileFirstGenericException(String message, Throwable cause) {
    super(message, cause);
  }

  public long getTimeStamp() {
    return timeStamp;
  }

  @Override
  public void printStackTrace() {
    if (getCause() != null) {
      // We probably don't want to print the trace of this wrapper exception, but rather the real exception
      getCause().printStackTrace();
    } else {
      super.printStackTrace();
    }
  }

  @Override
  public void printStackTrace(PrintStream s) {
    if (getCause() != null) {
      // We probably don't want to print the trace of this wrapper exception, but rather the real exception
      getCause().printStackTrace(s);
    } else {
      super.printStackTrace(s);
    }
  }

  @Override
  public void printStackTrace(PrintWriter s) {
    if (getCause() != null) {
      // We probably don't want to print the trace of this wrapper exception, but rather the real exception
      getCause().printStackTrace(s);
    } else {
      super.printStackTrace(s);
    }
  }
}

