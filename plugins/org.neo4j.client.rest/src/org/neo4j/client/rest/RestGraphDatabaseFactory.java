/**
 * 
 */
package org.neo4j.client.rest;

import java.net.URI;

import org.neo4j.client.GraphDatabaseFactory;
import org.neo4j.client.rest.impl.RestGraphDatabaseImpl;

/**
 * @author Ricker
 *
 */
public class RestGraphDatabaseFactory implements GraphDatabaseFactory {


	@Override
	public RestGraphDatabase createGraphDatabase() {
		return new RestGraphDatabaseImpl();
	}


	@Override
	public RestGraphDatabase createGraphDatabase(URI uri) {
		return new RestGraphDatabaseImpl(uri);
	}


	@Override
	public boolean hasScheme(String scheme) {
		return "http".equals(scheme) || "https".equals(scheme);
	}

}
