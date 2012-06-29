/**
 * 
 */
package org.neo4j.client;

import java.net.URI;

/**
 * Implementations of this interface should be discovered by injection.
 * 
 * @author Ricker
 * 
 */
public interface GraphDatabaseFactory {

	public GraphDatabase createGraphDatabase();

	public GraphDatabase createGraphDatabase(URI uri);

	/**
	 * {@link URI#getScheme()} is supported by this factory.
	 * 
	 * @param scheme
	 * @return
	 */
	public boolean hasScheme(String scheme);
}
