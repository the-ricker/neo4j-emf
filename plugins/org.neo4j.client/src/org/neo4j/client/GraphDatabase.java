/**
 * Copyright (c) 2002-2012 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.client;

import java.io.Closeable;

import org.neo4j.client.index.IndexManager;

/**
 * The main access point to a running Neo4j server.
 * 
 * GraphDatabase provides operations to {@link #createNode() create nodes},
 * {@link #getNodeById(long) get nodes given an id}, get the
 * {@link #getReferenceNode() reference node}.
 * <p>
 */
public interface GraphDatabase extends Closeable {

	/**
	 * Creates a new node.
	 * 
	 * @return the created node.
	 */
	public Node createNode();

	/**
	 * Looks up a node by id.
	 * 
	 * @param id
	 *            the id of the node
	 * @return the node with id <code>id</code> if found
	 * @throws NotFoundException
	 *             if not found
	 */
	public Node getNodeById(long id);

	/**
	 * Looks up a relationship by id.
	 * 
	 * @param id
	 *            the id of the relationship
	 * @return the relationship with id <code>id</code> if found
	 * @throws NotFoundException
	 *             if not found
	 */
	public Relationship getRelationshipById(long id);

	/**
	 * Returns the reference node, which is a "starting point" in the node
	 * space. Usually, a client attaches relationships to this node that leads
	 * into various parts of the node space. For more information about common
	 * node space organizational patterns, see the design guide at <a
	 * href="http://wiki.neo4j.org/content/Design_Guide"
	 * >wiki.neo4j.org/content/Design_Guide</a>.
	 * 
	 * @return the reference node
	 * @throws NotFoundException
	 *             if unable to get the reference node
	 */
	public Node getReferenceNode();

	/**
	 * Returns the {@link IndexManager} paired with this graph database service
	 * and is the entry point for managing indexes coupled with this database.
	 * 
	 * @return the {@link IndexManager} for this database.
	 */
	public IndexManager index();
}
