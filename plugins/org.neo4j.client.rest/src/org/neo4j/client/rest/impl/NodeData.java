/**
 * 
 */
package org.neo4j.client.rest.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ricker
 *
 */
public class NodeData extends PropertyContainerData {
	
	private String all_relationships;

	/**
	 * Read but do not write in JSON
	 * @return
	 */
	@JsonIgnore
	public String getAll_relationships() {
		return all_relationships;
	}

	@JsonProperty
	public void setAll_relationships(String all_relationships) {
		this.all_relationships = all_relationships;
	}
	
	

}
