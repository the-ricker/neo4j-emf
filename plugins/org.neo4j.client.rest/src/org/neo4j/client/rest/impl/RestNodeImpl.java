/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.client.Direction;
import org.neo4j.client.Node;
import org.neo4j.client.Relationship;
import org.neo4j.client.RelationshipType;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.RestNode;
import org.neo4j.client.rest.util.PathUtil;
import org.neo4j.client.rest.util.RelationshipUtil;

/**
 * @author Ricker
 * 
 */
public class RestNodeImpl extends PropertyContainerImpl implements RestNode {

	private Log log = LogFactory.getLog(RestNodeImpl.class);

	private NodeData data;
	private Collection<RestRelationshipImpl> relationships;
	private long id;

	protected RestNodeImpl(RestGraphDatabaseImpl graphDatabase, long id) {
		super(graphDatabase);
		this.id = id;
	}

	protected RestNodeImpl(RestGraphDatabaseImpl graphDatabase, NodeData data) {
		super(graphDatabase);
		setNodeData(data);
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
	public Iterable<Relationship> getRelationships() {
		return new HashSet<Relationship>(relationships);
	}

	@Override
	public boolean hasRelationship() {
		return !relationships.isEmpty();
	}

	@Override
	public Iterable<Relationship> getRelationships(RelationshipType... types) {
		return getRelationships(Direction.BOTH, types);
	}

	@Override
	public Iterable<Relationship> getRelationships(Direction direction, RelationshipType... types) {
		HashSet<Relationship> results = new HashSet<Relationship>();
		for (RestRelationshipImpl rel : relationships) {
			if (direction == Direction.BOTH || direction == RelationshipUtil.getDirection(this, rel)) {
				if (RelationshipUtil.intersects(rel.getType(), types)) {
					results.add(rel);
				}
			}
		}
		return results;
	}

	@Override
	public boolean hasRelationship(RelationshipType... types) {
		return hasRelationship(Direction.BOTH, types);
	}

	@Override
	public boolean hasRelationship(Direction direction, RelationshipType... types) {
		for (RestRelationshipImpl rel : relationships) {
			if (direction == Direction.BOTH || direction == RelationshipUtil.getDirection(this, rel)) {
				if (RelationshipUtil.intersects(rel.getType(), types)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public Iterable<Relationship> getRelationships(Direction dir) {
		if (dir == Direction.BOTH) {
			return getRelationships();
		}
		HashSet<Relationship> results = new HashSet<Relationship>();
		for (RestRelationshipImpl rel : relationships) {
			if (dir == RelationshipUtil.getDirection(this, rel)) {
				results.add(rel);
			}
		}
		return results;
	}

	@Override
	public boolean hasRelationship(Direction dir) {
		for (RestRelationshipImpl rel : relationships) {
			if (dir == Direction.BOTH || dir == RelationshipUtil.getDirection(this, rel)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Iterable<Relationship> getRelationships(RelationshipType type, Direction dir) {
		return getRelationships(dir,type);
	}

	@Override
	public boolean hasRelationship(RelationshipType type, Direction dir) {
		return hasRelationship(dir,type);
	}

	@Override
	public Relationship getSingleRelationship(RelationshipType type, Direction dir) {
		for (RestRelationshipImpl rel : relationships) {
			if (dir == Direction.BOTH || dir == RelationshipUtil.getDirection(this, rel)) {
				if (RelationshipUtil.matches(rel.getType(), type)) {
					return rel;
				}
			}
		}
		return null;
	}

	@Override
	public Relationship createRelationshipTo(Node otherNode, RelationshipType type) {
		// TODO Auto-generated method stub
		return null;
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
	protected Map<String, Object> getData() {
		if (!isLoaded()) {
			try {
				load();
			} catch (RestClientException e) {
				log.error("Error getting node data", e);
			}
		}
		if (data == null || data.getData() == null) {
			return new HashMap<String, Object>(0);
		}
		return data.getData();
	}

	@Override
	public String getSelf() {
		return data.getSelf();
	}

	@Override
	protected void doLoad() throws RestClientException {
		// TODO Auto-generated method stub

	}

	public void setNodeData(NodeData nodeData) {
		this.data = nodeData;
		id = PathUtil.getNodeId(data.getSelf());
		setLoaded(System.currentTimeMillis());

	}

	public void setRelationshipData(Collection<RelationshipData> nodeRelationships) {
		// TODO Auto-generated method stub

	}

}