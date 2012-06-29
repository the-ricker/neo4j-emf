package org.neo4j.client.rest.util;

import org.neo4j.client.Direction;
import org.neo4j.client.RelationshipType;
import org.neo4j.client.rest.RestNode;
import org.neo4j.client.rest.RestRelationship;

public class RelationshipUtil {
	
	public static boolean intersects(RelationshipType source, RelationshipType... types){
		for (RelationshipType type : types) {
			if (matches(source,type)) {
				return true;
			}
		}
		return false;
	}

	public static boolean matches(RelationshipType source, RelationshipType target) {
		return source.name().equals(target.name());
	}
	
	
	public static Direction getDirection(RestNode node, RestRelationship relationship) {
		if (node.getId() == relationship.getEndNodeId()) {
			return Direction.INCOMING;
		}
		if (node.getId() == relationship.getStartNodeId()) {
			return Direction.OUTGOING;
		}
		return null;
	}
	
}
