package ufo.hazelcast.eventbus;

import java.io.Serializable;

public class EventId implements Serializable {

	private static final long serialVersionUID = 1L;

	final String eventName;
	final String eventId;
	final String nodeId;

	public EventId(String nodeId, String eventName, String eventId) {
		this.nodeId = nodeId;
		this.eventName = eventName;
		this.eventId = eventId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((eventId == null) ? 0 : eventId.hashCode());
		result = (prime * result) + ((eventName == null) ? 0 : eventName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		EventId other = (EventId) obj;
		if (eventId == null) {
			if (other.eventId != null) {
				return false;
			}
		} else if (!eventId.equals(other.eventId)) {
			return false;
		}
		if (eventName == null) {
			if (other.eventName != null) {
				return false;
			}
		} else if (!eventName.equals(other.eventName)) {
			return false;
		}
		return true;
	}

}
