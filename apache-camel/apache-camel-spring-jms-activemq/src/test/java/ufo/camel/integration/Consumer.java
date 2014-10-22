package ufo.camel.integration;

public interface Consumer<T> {

	public void handle(T value);

}
