package test.socket.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

import test.socket.server.Event;

public class ClientImpl implements Client {

	private final int port;
	private final String host;

	ClientImpl(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public String getHost() {
		return host;
	}


	@Override
	public <I extends Serializable, O extends Serializable> I send(String eventName, O message) {
		try (	Socket clientSocket = new Socket(host, port);
				ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
				ObjectInputStream inFromServer = new ObjectInputStream(clientSocket.getInputStream());
			) {
			Event<O> event = new Event<>();
			event.eventName = eventName;
			event.message = message;
			outToServer.writeObject(event);
			return (I)inFromServer.readObject();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
