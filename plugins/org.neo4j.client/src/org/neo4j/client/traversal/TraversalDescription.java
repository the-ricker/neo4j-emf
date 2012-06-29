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
package org.neo4j.client.traversal;

import org.neo4j.client.Node;
import org.neo4j.client.RelationshipType;

/**
 * Represents a description of a traversal. This interface describes the rules
 * and behavior of a traversal. A traversal description is immutable and each
 * method which adds or modifies the behavior returns a new instances that
 * includes the new modification, leaving the instance which returns the new
 * instance intact. For instance,
 * 
 * <pre>
 * TraversalDescription td = new TraversalDescriptionImpl();
 * td.depthFirst();
 * </pre>
 * 
 * is not going to modify td. you will need to reassign td, like
 * 
 * <pre>
 * td = td.depthFirst();
 * </pre>
 * <p>
 * When all the rules and behaviors have been described the traversal is started
 * by using {@link #traverse(Node)} where a starting node is supplied. The
 * {@link Traverser} that is returned is then used to step through the graph,
 * and return the positions that matches the rules.
 */
public interface TraversalDescription {

	TraversalDescription uniqueness(Uniqueness uniqueness);

	/**
	 * Adds {@code type} to the list of relationship types to traverse. There's
	 * no priority or order in which types to traverse.
	 * 
	 * @param type
	 *            the {@link RelationshipType} to add to the list of types to
	 *            traverse.
	 * @return a new traversal description with the new modifications.
	 */
	TraversalDescription relationships(RelationshipType type);

	TraversalDescription returnType(ReturnType type);
	
	TraversalDescription returnFilter(ReturnFilter filter);
	
	TraversalDescription returnFilter(Script filter);
	
	TraversalDescription pruneEvaluator(Script evaluator);
	
	TraversalDescription maxDepth(int depth);
	
	/**
     * A convenience method for {@link #order(BranchOrderingPolicy)}
     * where a "preorder depth first" selector is used. Positions which are
     * deeper than the current position will be returned before positions on
     * the same depth. See http://en.wikipedia.org/wiki/Depth-first_search
     * @return a new traversal description with the new modifications.
     */
    TraversalDescription depthFirst();

    /**
     * A convenience method for {@link #order(BranchOrderingPolicy)}
     * where a "preorder breadth first" selector is used. All positions with
     * the same depth will be returned before advancing to the next depth.
     * See http://en.wikipedia.org/wiki/Breadth-first_search
     * @return a new traversal description with the new modifications.
     */
    TraversalDescription breadthFirst();
}
