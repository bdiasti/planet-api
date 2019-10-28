package br.com.b2w.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

/**
 * @author bdiasti
 *
 * Classe que define um erro para o usuário
 *
 */
public class ErrorDetails {
 
	private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private String details;
    
	public ErrorDetails(HttpStatus httpStatus, LocalDateTime timestamp, String message, String details) {
		super();
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
   
    
	
    
    
    
}
