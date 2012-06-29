/**
 * 
 */
package org.neo4j.client.rest.impl;

import java.util.Map;

import org.neo4j.client.PropertyContainer;
import org.neo4j.client.rest.RestClientException;
import org.neo4j.client.rest.RestGraphDatabase;

/**
 * @author Ricker
 *
 */
public abstract class PropertyContainerImpl implements PropertyContainer{
	
	protected RestGraphDatabaseImpl graphDatabase;
	private long loaded;
	
	public PropertyContainerImpl(RestGraphDatabaseImpl graphDatabase){
		this.graphDatabase = graphDatabase;
		loaded = 0;
	}

	@Override
	public RestGraphDatabase getGraphDatabase() {
		return graphDatabase;
	}

	@Override
	public boolean hasProperty(String key) {
		return getData().containsKey(key);
	}

	@Override
	public Object getProperty(String key) {
		return getData().get(key);
	}

	@Override
	public Object getProperty(String key, Object defaultValue) {
		Object val = getData().get(key);
		if (val != null) {
			return val;
		}
		return defaultValue;
	}

	@Override
	public void setProperty(String key, Object value) {
		getData().put(key, value);
	}

	@Override
	public Object removeProperty(String key) {
		return getData().remove(key);
	}

	@Override
	public Iterable<String> getPropertyKeys() {
		return getData().keySet();
	}

	public boolean isLoaded() {
		return loaded > 0;
	}
	
	public synchronized void load() throws RestClientException {
		doLoad();
		setLoaded(System.currentTimeMillis());
	}
	
	/**
	 * Must not return null
	 * @return
	 */
	protected abstract Map<String, Object> getData();
	
	protected abstract void doLoad() throws RestClientException;

	public long getLoaded() {
		return loaded;
	}

	public void setLoaded(long loaded) {
		this.loaded = loaded;
	}
	
	public void checkLoad() {
		
	}
	
}
