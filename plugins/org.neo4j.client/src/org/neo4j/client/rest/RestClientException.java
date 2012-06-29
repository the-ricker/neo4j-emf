/**
 * 
 */
package org.neo4j.client.rest;

import java.io.IOException;

/**
 * @author Ricker
 *
 */
public class RestClientException extends IOException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2641974052760363549L;

	/**
	 * 
	 */
	public RestClientException() {
	}

	/**
	 * @param message
	 */
	public RestClientException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public RestClientException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public RestClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
