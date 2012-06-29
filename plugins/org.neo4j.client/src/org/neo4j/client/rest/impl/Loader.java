/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.net.URI;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.neo4j.client.rest.RestClientException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
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
		this.uri = uri;
		httpclient = new DefaultHttpClient();
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		if (uri != null) {
			loadData();
		}
	}

	public URI getUri() {
		return uri;
	}

	public void setUri(URI uri) {
		this.uri = uri;
		if (uri != null) {
			loadData();
		} else {
			data = null;
		}
	}

	

	protected void loadData() {
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
	
	public Future<NodeData> loadNode(final long nodeid) {
		return executor.submit(new Callable<NodeData>() {

			@Override
			public NodeData call() throws Exception {
				if (data == null) {
					throw new RestClientException("No database specified or available");
				}
				return null;
			}
			
		}
		);
	}

}
