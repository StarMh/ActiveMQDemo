package com.tgb.SpringActivemq.mq.producer.topic;

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

@Component("topicSender")
public class TopicSender {
	
	@Autowired
	@Qualifier("jmsTopicTemplate")
	private JmsTemplate jmsTemplate;
	
	/**
	 * 发送一条消息到指定的Topic
	 * 
	 * @author wangtao34
	 * @time 2017年8月7日 下午4:24:21
	 * @param destination
	 * @param message
	 */
	public void send(Destination destination,final String message){
		jmsTemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

}
