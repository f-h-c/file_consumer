package br.com.ilegra.file_consumer.exception;


public class CustomerException extends RuntimeException {

  private static final long serialVersionUID = -2142016664949006428L;

	public CustomerException(String message) {
    super(message);
  }
}
