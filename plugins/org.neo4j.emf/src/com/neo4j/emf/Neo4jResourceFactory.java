/**
 * 
 */
package com.neo4j.emf;

import org.eclipse.emf.ecore.resource.Resource;

/**
 * @author Ricker
 *
 */
public interface Neo4jResourceFactory extends Resource.Factory {
	
	public final static String PROTOCOL = "neo4j";

}
