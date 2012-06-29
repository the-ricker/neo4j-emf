/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is what actually gets persisted for a node in JSON.
 * 
 * @author Ricker
 * 
 */
public abstract class PropertyContainerData {

	private String self;
	private Map<String, Object> data;

	/**
	 * Read but do not write in JSON
	 * @return
	 */
	@JsonIgnore
	public String getSelf() {
		return self;
	}

	@JsonProperty
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
