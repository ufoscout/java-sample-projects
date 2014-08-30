package com.gulag.neo.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.gulag.neo.Peers;

/**
 * 
 * 
 * this is the annotation interface i'm using to mark up the persisted fields in
 * my POJO's it contains only two properties because this is no more then a
 * simple example i'm only differentiating between primitives that go as
 * properties and lists of objects that go as links and other nodes
 * 
 * i closed up all the properties as emun's because i don't like leaving open
 * strings laying about
 * 
 * the annotation is marked with a Retention for runtime since i'm analyzing the
 * POJO in runtime
 * 
 * @author gilad
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface Persistance {
	Type type() default Type.Property;
    Peers relationType() default Peers.NA;
    IndexType index() default IndexType.NA;

	public static enum Type {
		Property, Peer
	}

    public static enum IndexType {
        BTree,Timeline,NA
    }
}
