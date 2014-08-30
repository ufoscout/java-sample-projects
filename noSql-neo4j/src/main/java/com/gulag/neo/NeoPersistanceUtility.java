package com.gulag.neo;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.EmbeddedGraphDatabase;

import com.gulag.neo.annotations.Persistance;

public class NeoPersistanceUtility {

	// the store directory is equivalent to the name of the database schema
	private static String storeDir = "target/graphdb";

	// this is here because its only an example and i'm lazy
	private static GraphDatabaseService neo = new EmbeddedGraphDatabase(storeDir);

	/**
	 * according to the neo4j instructions, its crucial to shutdown the database
	 * before terminating the vm (i.e. this test) in case you forget to
	 * shutdown, the next time the database will initiate, it will take a moment
	 * to try and recover.
	 * 
	 */
	public static void shutdown() {
		neo.shutdown();
	}

	/**
	 * this is a simple finder, that utilizes the find by id method on neo
	 * 
	 * @param id
	 *            of the desired node
	 * @return Node the node sought for
	 */
	public static Node findById(long id) {
		Transaction tx = neo.beginTx();
		Node res = neo.getNodeById(id);
		tx.finish();
		return res;

	}

	/**
	 * this is a recursive method that analyzes a particular field on the POJO,
	 * if the annotation on the field indicates that this is a simple property,
	 * it will set that property if the annotation indicates that this is a
	 * relation, it will recursively create a node for the child object and link
	 * the original with the new
	 * 
	 * note: this implementation is very naive and handles a very specific
	 * scenario
	 * 
	 * @param node
	 * @param object
	 * @param field
	 * @param stack
	 * @throws RuntimeException
	 * @throws Exception
	 */
	private static void processFieldData(Node node, Object object, Field field,
			Map<Object, Node> stack) throws Exception {

		// this bit here has allot to do with processing the annotations that
		// are set on the POJO, the neo stuff come later on
		Annotation[] annotations = field.getAnnotations();
		for (Annotation annotation : annotations) {
			if (annotation instanceof Persistance) {
				Persistance p = (Persistance) annotation;
				log("processing annotation: " + p.type() + " " + p.relationType());

				if (p.type().equals(Persistance.Type.Property)) {
					// TODO: verify that the property is actually a primitive
					Object value = field.get(object);
					log("setting property: " + field.getName() + " " + value);

					// here is a neo setting of a property
					node.setProperty(field.getName(), value);

				} else if (p.type().equals(Persistance.Type.Peer)) {
					Object value = field.get(object);
					log("setting peer: " + field.getName() + " " + value);
					if (value instanceof List<?>) {
						for (Object item : (List<?>) value) {
							// here is another neo bit, where a new Node is
							// created by recursively calling on the converting
							// method with the child object
							// after the new node is handed back, i create the
							// relationship between the two
							Node otherNode = objectToNode(item, stack);
							node.createRelationshipTo(otherNode, p.relationType());
						}
					} else {
						// TODO: handle a nested object
					}
				}
			}
		}
	}

	/**
	 * this method converts an object into a node and returns it
	 * 
	 * @param object
	 * @param stack
	 * @return
	 * @throws RuntimeException
	 * @throws Exception
	 */
	private static Node objectToNode(Object object, Map<Object, Node> stack)
			throws RuntimeException, Exception {

		// the stack in this case is used prevent looping forever on a circular
		// data model. in case the stack is null, i assume this is the root
		// object, i create the stack and add the current node as the first one
		if (stack == null) {
			stack = new HashMap<Object, Node>();
		} else if (stack.containsKey(object)) {
			return stack.get(object);
		}
		Node node = neo.createNode();
		stack.put(object, node);
		Class<? extends Object> cls = object.getClass();

		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			// only persisting the fields that have my special annotation
			if (field.isAnnotationPresent(Persistance.class)) {
				processFieldData(node, object, field, stack);
			}
		}
		return node;

	}

	/**
	 * the save method takes in any object and provided it has the @Persistance
	 * annotation, it will convert it into the neo node structure the
	 * implementation is rather simple and straight forward, and the conversion
	 * into properties or links is according to the annotation specification
	 * 
	 * @param object
	 * @return long - the neo id of the root node
	 */
	public static long save(Object object) {

		Transaction tx = neo.beginTx();
		Node node = null;
		try {
			node = objectToNode(object, null);
			tx.success();
			return node.getId();
		} catch (Throwable t) {
			tx.failure();
			t.printStackTrace();
		} finally {
			tx.finish();
		}

		// getting here means something went wrong
		throw new RuntimeException();

	}

	public static void log(String message) {
		System.out.println(message);
	}

}
