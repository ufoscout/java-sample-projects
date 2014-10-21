package ufo.camel.integration.client;

public interface Consumer<T> {

	public void handle(T value);

}
