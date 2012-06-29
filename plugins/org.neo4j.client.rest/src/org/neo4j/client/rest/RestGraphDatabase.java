/**
 * 
 */
package org.neo4j.client.rest;

import java.net.URI;

import org.neo4j.client.GraphDatabase;

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


}
