/**
 * 
 */
package com.neo4j.emf.impl;

import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.neo4j.graphdb.Node;

/**
 * @author Ricker
 *
 */
public class Neo4jAdapter extends AdapterImpl {
	
	private Node node;
	
	public Neo4jAdapter(Node node) {
		this.node = node;
	}

	public Node getNode() {
		return node;
	}
	
}
