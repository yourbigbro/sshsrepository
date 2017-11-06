package com.itheima.customer;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.springframework.stereotype.Component;

@Component
public class queueReceiver implements MessageListener{

	@Override
	public void onMessage(Message message) {
		try {
			//注意，强制转换是在类型上加括号，而不是在待转换的数据上加括号
			//这里为了方便没有发送短信，而是只是打印了一下接收到的信息。
			System.out.println("电话是"+((MapMessage) message).getString("telephone"));
			System.out.println("发送信息的内容是"+((MapMessage) message).getString("content"));
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}

}
