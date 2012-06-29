/**
 * 
 */
package org.neo4j.client.rest.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.neo4j.client.Node;
import org.neo4j.client.rest.RestGraphDatabase;

/**
 * @author Ricker
 *
 */
public class TestRestGraphDatabaseImpl {
	
	
	
	
	@Test
	public void testGetReferenceNode() throws Exception {
		RestGraphDatabaseImpl db = new RestGraphDatabaseImpl();
		db.setURI(RestGraphDatabase.DEFAULT_URI);
		Node node = db.getReferenceNode();
		assertNotNull(node);
		
	}
	
	@Test
	public void testCreateNode() throws Exception {
		RestGraphDatabaseImpl db = new RestGraphDatabaseImpl();
		db.setURI(RestGraphDatabase.DEFAULT_URI);
		Node node = db.createNode();
		assertNotNull(node);
		assertTrue(node.getId() > 0);
		System.out.println("Node " + node.getId());
	}
	

}
