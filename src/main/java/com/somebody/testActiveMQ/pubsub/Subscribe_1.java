package com.somebody.testActiveMQ.pubsub;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Subscribe_1  implements Runnable , ExceptionListener
{
	private ConnectionFactory connectionFactory ;
	private Connection connection ;
	private Session session ; 
	private Destination destination ; 
	private MessageConsumer messageConsumer ; 
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{
				this.connectionFactory = new ActiveMQConnectionFactory("tcp://119.29.186.181:61616") ; 
				this.connection = this.connectionFactory.createConnection() ; 
				this.connection.start();  
				this.connection.setExceptionListener(this);
				this.session = this.connection.createSession(false, Session.AUTO_ACKNOWLEDGE) ; 
				this.destination = this.session.createTopic("test") ; 
				this.messageConsumer = this.session.createConsumer(this.destination) ;
				Message message = this.messageConsumer.receive(100000) ; 
				
				if(message instanceof TextMessage)
				{
					TextMessage textMessage = (TextMessage)message ; 
					System.out.println("receiver:  " + textMessage.getText());
				}
				else
				{
					System.out.println("receiver:  " + message);
				}
				
				this.messageConsumer.close();
				this.session.close();
				this.connection.close();
			}
			catch(Exception e)
			{
				e.printStackTrace(); 
			}
		
		}
	}

	@Override
	public void onException(JMSException arg0)
	{
		arg0.printStackTrace(); 
	} 
	
	public static void main(String[] args)
	{
		new Thread(new Subscribe_1()).start(); 
	}
}
