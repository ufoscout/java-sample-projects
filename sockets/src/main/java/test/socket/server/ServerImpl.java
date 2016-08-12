package test.socket.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class ServerImpl implements Server {

	private boolean started = false;
	private boolean closed = false;
	private final int port;
	private final ServerSocket ss;
	private final Map<String, Function<?, ?>> consumers = new ConcurrentHashMap<>();

	ServerImpl(int port) {
		this.port = port;
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getPort() {
		return port;
	}

	@Override
	public void close() {
		if (closed) {
			return;
		}
		try {
			ss.close();
			closed = true;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void start() {
		if (started) {
			return;
		}
		started = true;
		new Thread(() -> {
	        while (true && !closed) {
	            Socket clientSocket;
				try {
					clientSocket = ss.accept();
					try(
							ObjectOutputStream outToClient = new ObjectOutputStream(clientSocket.getOutputStream());
							ObjectInputStream inFromClient = new ObjectInputStream(clientSocket.getInputStream())) {

						Event<?> event = (Event<?>) inFromClient.readObject();
						outToClient.writeObject(getConsumer(event.eventName).apply(event.message));

					}
				} catch (Exception e) {
					System.err.println(e);
				}

	        }
		}).start();

	}

	@Override
	public <T, R> void consume(String eventName, Function<T, R> consumer) {
		consumers.put(eventName, consumer);
	}

	private Function<Object, ?> getConsumer(Object eventName) {
		Function<?, ?> consumer = consumers.get(eventName);
		if (consumer == null) {
			consumer = (message) -> {return null;};
		}
		return (Function<Object, ?>) consumer;
	}

}
