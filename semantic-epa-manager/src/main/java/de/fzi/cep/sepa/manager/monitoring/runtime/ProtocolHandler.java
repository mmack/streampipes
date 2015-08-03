package de.fzi.cep.sepa.manager.monitoring.runtime;

import de.fzi.cep.sepa.commons.messaging.IMessageListener;
import de.fzi.cep.sepa.commons.messaging.IMessagePublisher;

public interface ProtocolHandler {

	public IMessagePublisher getPublisher();
	public IMessageListener getConsumer();
	
}
