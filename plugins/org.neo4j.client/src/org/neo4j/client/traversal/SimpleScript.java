/**
 * 
 */
package org.neo4j.client.traversal;

/**
 * @author Ricker
 *
 */
public class SimpleScript implements Script {
	
	private String language;
	
	private String body;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	

}
