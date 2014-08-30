package com.gulag.neo.model;

import java.util.ArrayList;
import java.util.List;

import com.gulag.neo.Peers;
import com.gulag.neo.annotations.Persistance;

/**
 * 
 * 
 * 
 * this is my example POJO containing data and pear minimum functionality for
 * setters and getters i've set the fields declaration to public for easy access
 * in the runtime reflection, in real life i would probably keep them as private
 * and have a smart method for finding the getter according to a naming
 * convention.
 * 
 * i've loaded each property with an annotation to instruct the run time
 * processing on how to relate to the particular field
 * 
 * @author gilad
 */
public class Person {

	@Persistance(type = Persistance.Type.Property)
	public String name;

	@Persistance(type = Persistance.Type.Property)
	public String nickname;

	@Persistance(type = Persistance.Type.Peer, relationType = Peers.Friend)
	public List<Person> friends;

	@Persistance(type = Persistance.Type.Peer, relationType = Peers.Foe)
	public List<Person> foes;

	public Person(String name, String nickname) {
		super();
		this.name = name;
		this.nickname = nickname;

		friends = new ArrayList<Person>();
		foes = new ArrayList<Person>();
	}

	public String getName() {
		return name;
	}

	public String getNickname() {
		return nickname;
	}

	public void addFriend(Person friend) {
		friends.add(friend);
	}

	public void addFoe(Person foe) {
		foes.add(foe);
	}

	public void removeFriend(Person friend) {
		friends.remove(friend);
	}

	public void removeFoe(Person foe) {
		foes.remove(foe);
	}

}
