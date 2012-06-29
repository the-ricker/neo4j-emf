/**
 * 
 */
package org.neo4j.client.rest.impl;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON values provided by the Neo4j service root. All the values are URLs
 * except the version.
 * 
 * @author Ricker
 * 
 */
public class DatabaseData {

	private String cypher;
	private String node;
	private String relationshipIndex;
	private String relationshipTypes;
	private String neo4jVersion;
	private String batch;
	private String nodeIndex;
	private String referenceNode;

	public String getCypher() {
		return cypher;
	}

	public void setCypher(String cypher) {
		this.cypher = cypher;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	@JsonProperty("relationship_index")
	public String getRelationshipIndex() {
		return relationshipIndex;
	}

	public void setRelationshipIndex(String relationshipIndex) {
		this.relationshipIndex = relationshipIndex;
	}

	@JsonProperty("relationship_types")
	public String getRelationshipTypes() {
		return relationshipTypes;
	}

	public void setRelationshipTypes(String relationshipTypes) {
		this.relationshipTypes = relationshipTypes;
	}

	@JsonProperty("neo4j_version")
	public String getNeo4jVersion() {
		return neo4jVersion;
	}

	public void setNeo4jVersion(String neo4jVersion) {
		this.neo4jVersion = neo4jVersion;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	@JsonProperty("node_index")
	public String getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(String nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	@JsonProperty("reference_node")
	public String getReferenceNode() {
		return referenceNode;
	}

	public void setReferenceNode(String referenceNode) {
		this.referenceNode = referenceNode;
	}

}
