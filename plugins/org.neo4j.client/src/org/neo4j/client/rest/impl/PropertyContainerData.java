/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Map;

/**
 * This is what actually gets persisted for a node in JSON.
 * 
 * @author Ricker
 *
 */
public abstract class PropertyContainerData {
	
	private String self;
	private Map<String, Object> data;

	public String getSelf() {
		return self;
	}

	public void setSelf(String self) {
		this.self = self;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}


}
