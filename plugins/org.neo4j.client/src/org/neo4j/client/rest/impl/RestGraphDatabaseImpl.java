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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.neo4j.client.Node;
import org.neo4j.client.Relationship;
import org.neo4j.client.index.IndexManager;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.RestGraphDatabase;
import org.neo4j.client.rest.RestNode;
import org.neo4j.client.rest.util.PathUtil;

/**
 * Responsible for implementing the interface and maintaining the cache.
 * <p>
 * The loader is responsible for interacting with the database via HTTP.
 * 
 * @author Ricker
 * 
 */
public class RestGraphDatabaseImpl implements RestGraphDatabase {

	private Log log = LogFactory.getLog(RestGraphDatabaseImpl.class);

	/*
	 * @see http://www.codeinstructions.com/2008/09/weakhashmap-is-not-cache-
	 * understanding.html
	 */
	private Map<Long, SoftReference<RestNodeImpl>> nodes;
	private Map<Long, SoftReference<RelationshipImpl>> references;
	
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
			Long nodeId = PathUtil.getNodeId(getData().getReferenceNode());
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
	public HttpResponse execute(HttpUriRequest request) throws RestClientException {
		try {
			return httpclient.execute(request);
		} catch (Exception e) {
			throw new RestClientException(e);
		}
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}


	

}
