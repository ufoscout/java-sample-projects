package test.socket.server;

import java.io.Serializable;

public class Event<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	public String eventName;
	public T message;

}
