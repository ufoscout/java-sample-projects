package ufo.hazelcast.eventbus;

import java.io.Serializable;

public class EventData<I> implements Serializable {

	private static final long serialVersionUID = 1L;

	public final EventId eventId;
	public final I args;

	EventData(EventId eventId, I args) {
		this.eventId = eventId;
		this.args = args;
	}
}
