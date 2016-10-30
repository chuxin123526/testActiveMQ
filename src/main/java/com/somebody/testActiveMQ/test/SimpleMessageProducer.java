package com.somebody.testActiveMQ.test;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

@Component
public class SimpleMessageProducer
{
	@Autowired
	protected JmsTemplate producerTemplate;

	protected int numberOfMessages = 100;
	
	StringBuilder payload = null;
	
	int i = 0 ; 

	public void sendMessages() throws JMSException
	{
		for ( ; i < numberOfMessages; ++i)
		{

			payload = new StringBuilder();

			payload.append("Message [").append(i).append("] sent at: ").append(new Date());

			producerTemplate.send(new MessageCreator()
			{

				public Message createMessage(Session session) throws JMSException
				{

					TextMessage message = session.createTextMessage(payload.toString());

					message.setIntProperty("messageCount", i);

					System.out.println("Sending message number [" + i + "]");

					return message;

				}

			});

		}

	}

}
