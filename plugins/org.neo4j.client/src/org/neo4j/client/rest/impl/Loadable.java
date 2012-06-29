/**
 * 
 */
package org.neo4j.client.rest.impl;

import org.neo4j.client.rest.RestClientException;

/**
 * @author Ricker
 *
 */
public interface Loadable {
	
	public long getLoadTime();
	
	public boolean isLoaded();
	
	public void markLoadTime();
	
	public void load() throws RestClientException;

}
