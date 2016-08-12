package test.socket.server;

import java.util.function.Function;

public interface Server {

	public static Server build(int port, boolean autoIncrement) {
		boolean first = true;
		while ( first || autoIncrement ) {
			first = false;
			try {
				return new ServerImpl(port++);
			} catch (RuntimeException e) {
				// do nothing
			}
		}
		throw new RuntimeException("No free port found");
	};

	int getPort();

	void start();

	void close();

	<T, R> void consume( String eventName, Function<T, R> consumer );
}
