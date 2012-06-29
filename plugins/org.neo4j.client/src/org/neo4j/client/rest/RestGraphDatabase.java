/**
 * 
 */
package org.neo4j.client.rest;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.neo4j.client.GraphDatabase;
import org.neo4j.client.rest.RestClientException;

/**
 * @author Ricker
 * 
 */
public interface RestGraphDatabase extends GraphDatabase {

	public static String DEFAULT_URI = "http://localhost:7474/db/data/";

	/**
	 * The URI for the database
	 * 
	 * @return
	 */
	public URI getURI();

	/**
	 * Provide service for HTTP requests
	 * @param request
	 * @return
	 * @throws RestClientException
	 */
//	public HttpResponse execute(HttpUriRequest request) throws RestClientException;

}
