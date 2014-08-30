package ufo.hazelcast.eventbus;

public interface EventResponse<O> {

	void receive(O response);

}
