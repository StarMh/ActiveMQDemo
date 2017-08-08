package com.tgb.SpringActivemq.mq.listener;

import java.util.LinkedList;
import java.util.Queue;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * 
 * @author wangtao34
 * @time 2017年8月7日 下午8:31:30
 */
public class TopicMessageListener implements MessageListener {
	public Queue<Message> messages = new LinkedList<Message>();
	public int mark;

	public TopicMessageListener() {
		super();
		mark = (int)(Math.random()*100);
		System.err.println("\n---------------\n");
		System.err.println("新建topic监听器"+mark);
		System.err.println("\n---------------\n");
	}



	// 当收到消息后，自动调用该方法
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println("TopicMessageListener"+mark+"监听到了文本消息listener：\t" + tm.getText());
				messages.add(message);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
