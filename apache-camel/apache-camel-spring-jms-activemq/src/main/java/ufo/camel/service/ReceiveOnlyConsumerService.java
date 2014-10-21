package ufo.camel.service;

import java.util.List;

public interface ReceiveOnlyConsumerService {

	List<String> getConsumedValues();

	void valueConsumer(String value);

}
