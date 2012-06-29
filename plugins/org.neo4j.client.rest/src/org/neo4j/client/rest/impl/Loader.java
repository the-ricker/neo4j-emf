/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.net.URI;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.util.PathUtil;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Handles the REST requests to the database server via HTTP. 
 * Most methods return Future so that the caller can determine how
 * long they wish to wait for results.
 * 
 * @author Ricker
 * 
 */
public class Loader {

	private Log log = LogFactory.getLog(Loader.class);

	private ExecutorService executor;
	private ObjectMapper mapper;
	private HttpClient httpclient;
	private URI uri;
	private DatabaseData data;

	public Loader(URI uri) {
		httpclient = new DefaultHttpClient();
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		executor = Executors.newCachedThreadPool();
		setUri(uri);
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
		if (uri != null) {
			loadDatabaseData();
		} else {
			data = null;
		}
	}

	protected void loadDatabaseData() {
		executor.submit(new Runnable() {

			@Override
			public void run() {
				try {
					HttpGet request = new HttpGet(uri);
					request.setHeader("Accept", "application/json");
					HttpResponse response = httpclient.execute(request);
					data = mapper.readValue(response.getEntity().getContent(), DatabaseData.class);
				} catch (Exception e) {
					log.error("Could not load database data for " + uri, e);
				}
			}

		});
	}

	public Future<NodeData> createNode() {
		return executor.submit(new Callable<NodeData>() {

			@Override
			public NodeData call() throws Exception {
				if (data == null) {
					throw new RestClientException("No database specified or available");
				}
				HttpPost request = new HttpPost(data.getNode());
				request.setHeader("Accept", "application/json");
				HttpResponse response = httpclient.execute(request);
				NodeData nodeData = mapper.readValue(response.getEntity().getContent(), NodeData.class);
				return nodeData;
			}

		});
	}

	public Future<NodeData> loadNode(final long nodeId) {
		return executor.submit(new Callable<NodeData>() {

			@Override
			public NodeData call() throws Exception {
				if (data == null) {
					throw new RestClientException("No database specified or available");
				}
				String path = data.getNode() + "/" + Long.toString(nodeId);
				HttpGet request = new HttpGet(path);
				request.setHeader("Accept", "application/json");
				HttpResponse response = httpclient.execute(request);
				return mapper.readValue(response.getEntity().getContent(), NodeData.class);
			}

		});
	}

	public Future<Collection<RelationshipData>> loadNodeRelationships(final NodeData nodeData) {
		return executor.submit(new Callable<Collection<RelationshipData>>() {

			@Override
			public Collection<RelationshipData> call() throws Exception {
				if (data == null) {
					throw new RestClientException("No database specified or available");
				}
				HttpGet request = new HttpGet(nodeData.getAll_relationships());
				request.setHeader("Accept", "application/json");
				HttpResponse response = httpclient.execute(request);
				return mapper.readValue(response.getEntity().getContent(),
						new TypeReference<Collection<RelationshipData>>() {
						});
			}

		});

	}

	public long getReferenceNodeId() throws RestClientException {
		if (data == null) {
			throw new RestClientException("No database specified or available");
		}
		return PathUtil.getNodeId(data.getReferenceNode());
	}

	public void shutdown() {
		executor.shutdown();
	}

}
