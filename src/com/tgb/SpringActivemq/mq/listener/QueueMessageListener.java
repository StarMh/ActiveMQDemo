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
 * @time 2017年8月7日 下午4:37:48
 */
public class QueueMessageListener implements MessageListener {
	public Queue<Message> messages = new LinkedList<Message>();
	private int mark;
	public QueueMessageListener() {
		super();
		mark = (int)(Math.random()*100);
		System.err.println("\n---------------\n");
		System.err.println("新建queue监听器"+mark);
		System.err.println("\n---------------\n");
	}
	// 当收到消息后，自动调用该方法
	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage tm = (TextMessage) message;
			try {
				System.out.println("QueueMessageListener"+mark+"监听到了文本消息listener：\t" + tm.getText());
				messages.add(message);
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
