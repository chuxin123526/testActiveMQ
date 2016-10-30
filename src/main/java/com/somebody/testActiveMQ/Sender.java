package com.somebody.testActiveMQ;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class Sender
{
	private static ConnectionFactory connectionFactory ;
	private static  Connection connection ;
	private static  Session session ; 
	private static  Destination destination ; 
	private static  MessageProducer messageProducer ; 
	
	public static void main(String[] args) throws Exception
	{
		connectionFactory = new ActiveMQConnectionFactory("tcp://119.29.186.181:61616") ; 
		connection =connectionFactory.createConnection() ; 
		connection.start() ; 
		session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE) ; 
		destination = session.createQueue("test") ; 
		messageProducer = session.createProducer(destination) ; 
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		String text = "test activeMQ" ; 
		TextMessage textMessage = session.createTextMessage(text) ; 
		messageProducer.send(textMessage);
		messageProducer.close();
		session.close();
		connection.close();
	}
	
}
