/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Map;

import org.neo4j.client.Node;
import org.neo4j.client.RelationshipType;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.RestGraphDatabase;
import org.neo4j.client.rest.RestRelationship;
import org.neo4j.client.rest.util.PathUtil;

/**
 * @author Ricker
 *
 */
public class RelationshipImpl extends PropertyContainerImpl implements RestRelationship {

	private long id;
	private RelationshipData data;
	
	/**
	 * @param graphDatabase
	 */
	public RelationshipImpl(RestGraphDatabase graphDatabase, RelationshipData data) {
		super(graphDatabase);
		this.data = data;
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
		checkLoad();
		if (data != null) {
			long nodeId = PathUtil.getNodeId(data.getStart());
			return getGraphDatabase().getNodeById(nodeId);
		}
		return null;
	}


	@Override
	public Node getEndNode() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.neo4j.client.Relationship#getOtherNode(org.neo4j.client.Node)
	 */
	@Override
	public Node getOtherNode(Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.neo4j.client.Relationship#getNodes()
	 */
	@Override
	public Node[] getNodes() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.neo4j.client.Relationship#getType()
	 */
	@Override
	public RelationshipType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see org.neo4j.client.Relationship#isType(org.neo4j.client.RelationshipType)
	 */
	@Override
	public boolean isType(RelationshipType type) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see org.neo4j.client.impl.PropertyContainerImpl#doLoad()
	 */
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

	



}
