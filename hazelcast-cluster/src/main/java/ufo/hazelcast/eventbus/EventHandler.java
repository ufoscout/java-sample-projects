package ufo.hazelcast.eventbus;

public interface EventHandler<I,O> {

	O process(EventData<I> eventData);

}
