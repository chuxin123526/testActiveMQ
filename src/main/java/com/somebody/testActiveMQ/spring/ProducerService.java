package com.somebody.testActiveMQ.spring;

import javax.jms.Destination;

public interface ProducerService
{
	public void sendMessage(Destination destination, final String message) ;
}
