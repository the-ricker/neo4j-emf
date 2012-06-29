package org.neo4j.client.rest.util;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Test;

public class TestPathUtil {
	
	@Test
	public void testGetNodeId() {
		String path = "http://localhost:7474/db/data/node/123";
		Long nodeId = PathUtil.getNodeId(path);
		assertEquals(Long.valueOf(123L), nodeId);
	}
	
	@Test
	public void testCreateNodePath() throws URISyntaxException {
		URI root = new URI("http://localhost:7474/db/data");
		long nodeId = 123L;
		String path = PathUtil.createNodePath(root, nodeId);
		assertEquals("http://localhost:7474/db/data/node/123" , path);
	}

}
