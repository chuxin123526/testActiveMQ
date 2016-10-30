package com.somebody.testActiveMQ.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{
	@org.junit.Test
	public void testspringActiveMQ() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("com/somebody/testActiveMQ/test/applicationContext.xml") ; 
		SimpleMessageProducer messageProducer = (SimpleMessageProducer)applicationContext.getBean("simpleMessageProducer") ; 
		messageProducer.sendMessages();
	}
}
