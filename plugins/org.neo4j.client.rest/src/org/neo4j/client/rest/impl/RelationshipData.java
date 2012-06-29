package org.neo4j.client.rest.impl;

public class RelationshipData extends PropertyContainerData {
	
	private String start;
	
	private String end;
	
	private String type;

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
