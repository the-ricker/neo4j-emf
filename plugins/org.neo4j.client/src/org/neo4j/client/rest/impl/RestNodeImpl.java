/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.neo4j.client.Direction;
import org.neo4j.client.Node;
import org.neo4j.client.Relationship;
import org.neo4j.client.RelationshipType;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.RestGraphDatabase;
import org.neo4j.client.rest.RestNode;
import org.neo4j.client.rest.util.PathUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Ricker
 * 
 */
public class RestNodeImpl extends PropertyContainerImpl implements RestNode {

	private Log log = LogFactory.getLog(RestNodeImpl.class);

	private NodeData data;
	private Collection<RelationshipData> relationships;
	private long id;

	public RestNodeImpl(RestGraphDatabaseImpl graphDatabase, long id) {
		super(graphDatabase);
		this.id = id;
	}

	public RestNodeImpl(RestGraphDatabaseImpl graphDatabase, NodeData data) {
		super(graphDatabase);
		this.data = data;
		id = PathUtil.getNodeId(data.getSelf());
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
	public Iterable<Relationship> getRelationships() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRelationship() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Relationship> getRelationships(RelationshipType... types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<Relationship> getRelationships(Direction direction, RelationshipType... types) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRelationship(RelationshipType... types) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasRelationship(Direction direction, RelationshipType... types) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Relationship> getRelationships(Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRelationship(Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Relationship> getRelationships(RelationshipType type, Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasRelationship(RelationshipType type, Direction dir) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Relationship getSingleRelationship(RelationshipType type, Direction dir) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relationship createRelationshipTo(Node otherNode, RelationshipType type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void doLoad() throws RestClientException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		/*
		 * load data
		 */
		HttpGet req = new HttpGet(getURI());
		req.setHeader("Accept", "application/json");
		HttpResponse response = getGraphDatabase().execute(req);
		try {
			data = mapper.readValue(response.getEntity().getContent(), NodeData.class);
		} catch (Exception e) {
			throw new RestClientException(e);
		}
		/*
		 * load relationships
		 */
		req = new HttpGet(getURI());
		req.setHeader("Accept", "application/json");
		response = getGraphDatabase().execute(req);
		try {
			relationships = mapper.readValue(response.getEntity().getContent(),
					new TypeReference<Collection<RelationshipData>>() {
					});
		} catch (Exception e) {
			throw new RestClientException(e);
		}
	}

	public String getURI() {
		if (isLoaded()) {
			return data.getSelf();
		}
		return getGraphDatabase().getURI().toString() + "/node/" + getId();
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
		// TODO Auto-generated method stub
		return null;
	}

}