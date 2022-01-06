package sg.edu.nus.se.its.exception;

/**
 * Custom exception to track matching errors. TODO YN: check if needed
 */
public class UnmatchedException extends Exception {
  public UnmatchedException(String message) {
    super(message);
  }
}
