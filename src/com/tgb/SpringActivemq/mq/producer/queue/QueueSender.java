package com.tgb.SpringActivemq.mq.producer.queue;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author wangtao34
 *
 */

@Component("queueSender")
public class QueueSender {

	@Autowired
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;// 通过@Qualifier修饰符来注入对应的bean

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param destination
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	public void send(Destination destination,final String message){
		System.out.println("发送queue消息:"+message);
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

}
