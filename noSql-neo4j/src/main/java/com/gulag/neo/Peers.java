package com.gulag.neo;

import org.neo4j.graphdb.RelationshipType;

/**
 * this is the enum that implements the neo4j RelationType. it is essential to
 * declare the relation type so that the database graph would have the domain
 * jargon and thus clearer when attempting to query it
 * 
 * @author gilad
 */
public enum Peers implements RelationshipType   {
	Friend, Foe, NA
}
