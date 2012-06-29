/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Map;

import org.neo4j.client.DynamicRelationshipType;
import org.neo4j.client.Node;
import org.neo4j.client.RelationshipType;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.RestRelationship;
import org.neo4j.client.rest.util.PathUtil;

/**
 * The connection of the Relationship to the Nodes is weak. 
 * 
 * @author Ricker
 * 
 */
public class RestRelationshipImpl extends PropertyContainerImpl implements RestRelationship {

	private long id;
	private RelationshipData data;

	/**
	 * @param graphDatabase
	 */
	protected RestRelationshipImpl(RestGraphDatabaseImpl graphDatabase, RelationshipData data) {
		super(graphDatabase);
		setRelationshipData(data);
	}

	protected RestRelationshipImpl(RestGraphDatabaseImpl graphDatabase, long id) {
		super(graphDatabase);
		this.id = id;
	}
	
	public void setRelationshipData(RelationshipData data) {
		this.data = data;
		id = PathUtil.getRelationshipId(data.getSelf());
		setLoaded(System.currentTimeMillis());
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub

	}

	@Override
	public Node getStartNode() {
		long nodeId = PathUtil.getNodeId(data.getStart());
		return graphDatabase.getNodeById(nodeId);
	}

	@Override
	public Node getEndNode() {
		long nodeId = PathUtil.getNodeId(data.getEnd());
		return graphDatabase.getNodeById(nodeId);
	}


	@Override
	public Node getOtherNode(Node node) {
		if (node != null) {
			if (node.getId() == Long.parseLong(data.getEnd())) {
				return getStartNode();
			}
			if (node.getId() == Long.parseLong(data.getStart())) {
				return getEndNode();
			}
		}
		return null;
	}


	@Override
	public Node[] getNodes() {
		return new Node[]{getStartNode(), getEndNode()};
	}


	@Override
	public RelationshipType getType() {
		return DynamicRelationshipType.withName(data.getType());
	}

	@Override
	public boolean isType(RelationshipType type) {
		return type.equals(getType());
	}


	@Override
	protected void doLoad() throws RestClientException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void save() throws RestClientException {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSelf() {
		return data.getSelf();
	}

	@Override
	protected Map<String, Object> getData() {
		return data.getData();
	}

}
