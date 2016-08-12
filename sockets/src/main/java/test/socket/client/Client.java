package test.socket.client;

import java.io.Serializable;

public interface Client {

	public static Client build(String host, int port) {
		return new ClientImpl(host, port);
	}

	int getPort();

	String getHost();

	public <I extends Serializable, O extends Serializable> I send(String eventName, O message);

}
