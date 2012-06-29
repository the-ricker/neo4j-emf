/**
 * 
 */
package org.neo4j.client.rest.util;

import java.net.URI;

/**
 * @author Ricker
 *
 */
public class PathUtil {
	
	public static Long getNodeId(String path) {
		if (path != null && path.lastIndexOf('/') > -1) {
			return Long.parseLong(path.substring(path.lastIndexOf('/')+1));
		}
		return 0L;
	}
	
	public static String createNodePath(URI root, Long nodeId) {
		StringBuilder buf = new StringBuilder();
		buf.append(root.toString());
		buf.append("/node/");
		buf.append(Long.toString(nodeId));
		return buf.toString();
	}

	public static long getRelationshipId(String path) {
		if (path != null && path.lastIndexOf('/') > -1) {
			return Long.parseLong(path.substring(path.lastIndexOf('/')+1));
		}
		return 0L;
	}

}
