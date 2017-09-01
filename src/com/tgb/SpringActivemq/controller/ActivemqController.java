package com.tgb.SpringActivemq.controller;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tgb.SpringActivemq.mq.producer.queue.QueueSender;
import com.tgb.SpringActivemq.mq.producer.topic.TopicSender;

/**
 * 
 * @author wangtao34
 *
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	private static int tl = 1;
	private static int ql = 1;
	@Resource(name = "queueDestination")
	private Destination queueDestination;
	@Resource(name = "topicDestination")
	private Destination topicDestination;

//	@Resource(name = "queueListenerContainer1")
//	private DefaultMessageListenerContainer queueListenerContainer1;
//	@Resource(name = "queueListenerContainer2")
//	private DefaultMessageListenerContainer queueListenerContainer2;
//	@Resource(name = "topicListenerContainer1")
//	private DefaultMessageListenerContainer topicListenerContainer1;
//	@Resource(name = "topicListenerContainer2")
//	private DefaultMessageListenerContainer topicListenerContainer2;

	@Resource
	QueueSender queueSender;
	@Resource
	TopicSender topicSender;
//	@Resource
//	TopicReceiver topicReceiver1;
//	@Resource
//	QueueReceiver queueReceiver1;
//	@Resource
//	TopicReceiver topicReceiver2;
//	@Resource
//	QueueReceiver queueReceiver2;
	
	/**
	 * 刷新并返回队列监听器1的消息
	 * @author wangtao34
	 * @time 2017年8月7日 下午4:57:10
	 * @return
	 */
//	@ResponseBody
//	@RequestMapping(value = "refreshQueueListener1", produces={"text/html;charset=UTF-8;","application/json;"})
//	public String refreshQueueListener1() {
//		StringBuilder opt = new StringBuilder();
//		QueueMessageListener queueMessageListener = (QueueMessageListener) queueListenerContainer1.getMessageListener();
//		try {
//			Queue<Message> queue = queueMessageListener.messages;
//			while(!queue.isEmpty()){
//				opt.append(((TextMessage)queue.remove()).getText());
//				opt.append("\n");
//			}
//		} catch (Exception e) {
//			opt = new StringBuilder("error");
//		}
//		return opt.toString();
//	}
//	/**
//	 * 刷新并返回队列监听器1的消息
//	 * @author wangtao34
//	 * @time 2017年8月7日 下午4:57:10
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="refreshQueueListener2", produces={"text/html;charset=UTF-8;","application/json;"})
//	public String refreshQueueListener2() {
//		StringBuilder opt = new StringBuilder("");
//		QueueMessageListener queueMessageListener = (QueueMessageListener) queueListenerContainer2.getMessageListener();
//		try {
//			Queue<Message> queue = queueMessageListener.messages;
//			while(!queue.isEmpty()){
//				opt.append(((TextMessage)queue.remove()).getText());
//				opt.append("\n");
//			}
//		} catch (Exception e) {
//			opt = new StringBuilder("error");
//		}
//		return opt.toString();
//	}
//	/**
//	 * 刷新并返回TOPIC监听器1的消息
//	 * @author wangtao34
//	 * @time 2017年8月7日 下午4:57:10
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "refreshTopicListener1",produces={"text/html;charset=UTF-8;","application/json;"})
//	public String refreshTopicListener1() {
//		StringBuilder opt = new StringBuilder("");
//		TopicMessageListener topicMessageListener = (TopicMessageListener) topicListenerContainer1.getMessageListener();
//		try {
//			Queue<Message> queue = topicMessageListener.messages;
//			while(!queue.isEmpty()){
//				opt.append(((TextMessage)queue.remove()).getText());
//				opt.append("\n");
//			}
//		} catch (Exception e) {
//			opt = new StringBuilder("error");
//		}
//		return opt.toString();
//	}
//	/**
//	 * 刷新并返回TOPIC监听器2的消息
//	 * @author wangtao34
//	 * @time 2017年8月7日 下午4:57:10
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "refreshTopicListener2", produces={"text/html;charset=UTF-8;","application/json;"})
//	public String refreshTopicListener2() {
//		StringBuilder opt = new StringBuilder("");
//		TopicMessageListener topicMessageListener = (TopicMessageListener) topicListenerContainer2.getMessageListener();
//		try {
//			Queue<Message> queue = topicMessageListener.messages;
//			while(!queue.isEmpty()){
//				opt.append(((TextMessage)queue.remove()).getText());
//				opt.append("\n");
//			}
//		} catch (Exception e) {
//			opt = new StringBuilder("error");
//		}
//		return opt.toString();
//	}
//
	/**
	 * 发送消息到队列 Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * 
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("queueSender")
	public String queueSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			queueSender.send(queueDestination, message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}

	/**
	 * 发送消息到主题 Topic主题 ：放入一个消息，所有订阅者都会收到 这个是主题目的地是一对多的
	 * 
	 * @param message
	 * @return String
	 */
	@ResponseBody
	@RequestMapping("topicSender")
	public String topicSender(@RequestParam("message") String message) {
		String opt = "";
		try {
			topicSender.send(topicDestination, message);
			opt = "suc";
		} catch (Exception e) {
			opt = e.getCause().toString();
		}
		return opt;
	}
	/**
	 * 跳转控制
	 * 
	 * @author wangtao34
	 * @time 2017年8月11日 下午5:04:51
	 * @param model
	 * @return
	 */
	@RequestMapping("/topiclistener")
	public String topiclistener(ModelMap model) {
		model.addAttribute("name",Integer.valueOf(tl));
		tl++;
		return "topicwebsocket";
	}
	@RequestMapping("/queuelistener")
	public String queuelistener(ModelMap model) {
		model.addAttribute("name",Integer.valueOf(ql));
		ql++;
		return "queuewebsocket";
	}

}
