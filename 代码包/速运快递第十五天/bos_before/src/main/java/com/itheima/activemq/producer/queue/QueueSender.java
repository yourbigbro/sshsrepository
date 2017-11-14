package com.itheima.activemq.producer.queue;

import java.util.Map;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * @description 队列消息生产者，发送消息到队列
 * 
 */
//下面的这个注解使得本项目中可以通过Autowired注解使用queueSender类进而使用类中的方法。@Component是原始的注解。
@Component("queueSender")
public class QueueSender {

	@Autowired
	//jmsQueueTemplate对应的是id的值
	@Qualifier("jmsQueueTemplate")
	private JmsTemplate jmsTemplate;// 通过@Qualifier修饰符来注入对应的bean

	/**
	 * 发送一条消息到指定的队列（目标）
	 * 
	 * @param queueName
	 *            队列名称
	 * @param message
	 *            消息内容
	 */
	//该方法用于发送字符串类型的消息
	//方法上面是不用注解的。只有类和变量上面需要注解
	public void send(String queueName, final String message) {
		//使用了jmsTemplate
		jmsTemplate.send(queueName, new MessageCreator() {
			//Message应该是TextMessage的父类
			public Message createMessage(Session session) throws JMSException {
				//注意和下面的createMapMessage做对比
				//将参数直接传进去
				return session.createTextMessage(message);
			}
		});
	}

	//该方法用于发送map类型的消息
	public void send(String queueName, final Map<String, String> map) {
		//使用了jmsTemplate
		jmsTemplate.send(queueName, new MessageCreator() {
			//Message应该是MapMessage的父类
			public Message createMessage(Session session) throws JMSException {
				//注意和上面的createTextMessage做对比
				MapMessage message = session.createMapMessage();
				for (String key : map.keySet()) {
					//将参数用setString方法传进去(传进去的依然是键值对)
					message.setString(key, map.get(key));
				}
				return message;
			}
		});
	}

}
