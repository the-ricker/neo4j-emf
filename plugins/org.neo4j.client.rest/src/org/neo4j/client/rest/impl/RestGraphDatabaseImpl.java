/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.neo4j.client.Node;
import org.neo4j.client.Relationship;
import org.neo4j.client.index.IndexManager;
import org.neo4j.client.rest.RestGraphDatabase;
import org.neo4j.client.rest.RestNode;

/**
 * Responsible for implementing the interface and maintaining the cache.
 * <p>
 * The loader is responsible for interacting with the database via HTTP.
 * 
 * @author Ricker
 * 
 */
public class RestGraphDatabaseImpl implements RestGraphDatabase {

	public static final long DEFAULT_TIMEOUT = 2000;
	
	private Log log = LogFactory.getLog(RestGraphDatabaseImpl.class);

	/*
	 * @see http://www.codeinstructions.com/2008/09/weakhashmap-is-not-cache-
	 * understanding.html
	 */
	private Map<Long, SoftReference<RestNodeImpl>> nodes;
	private Map<Long, SoftReference<RestRelationshipImpl>> relationships;
	private long timeout = DEFAULT_TIMEOUT;
	private DatabaseData data;
	private Loader loader;
	
	
	public RestGraphDatabaseImpl() {
		this(null);
	}

	public RestGraphDatabaseImpl(URI uri) {
		loader = new Loader(uri);
		nodes = new WeakHashMap<Long, SoftReference<RestNodeImpl>>();
	}

	@Override
	public URI getURI() {
		return loader.getUri();
	}

	public void setURI(String uri) throws URISyntaxException {
		loader.setUri(new URI(uri));
	}

	public void setURI(URI uri) {
		loader.setUri(uri);
	}

	@Override
	public RestNode createNode() {
		try {
			Future<NodeData> req = loader.createNode();
			RestNodeImpl node = new RestNodeImpl(this, req.get(timeout, TimeUnit.MILLISECONDS));
			cacheNode(node);
			return node;
		} catch (Exception e) {
			log.error("Failed to get reference node", e);
		}
		return null;
	}

	@Override
	public RestNode getNodeById(long id) {
		RestNodeImpl node = lookupNode(id);
		if (node != null) {
			return node;
		}
		node = new RestNodeImpl(this, id);
		cacheNode(node);
		return node;
	}

	protected RestNodeImpl lookupNode(long id) {
		SoftReference<RestNodeImpl> ref = nodes.get(id);
		if (ref != null) {
			return ref.get();
		}
		return null;
	}

	protected void cacheNode(RestNodeImpl node) {
		nodes.put(node.getId(), new SoftReference<RestNodeImpl>(node));
	}

	@Override
	public Relationship getRelationshipById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Node getReferenceNode() {
		try {
			Long nodeId = loader.getReferenceNodeId();
			return getNodeById(nodeId);
		} catch (Exception e) {
			log.error("Failed to get reference node", e);
		}
		return null;
	}

	@Override
	public IndexManager index() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void close() throws IOException {
		loader.shutdown();
		nodes.clear();
		relationships.clear();
	}

	
	
}
