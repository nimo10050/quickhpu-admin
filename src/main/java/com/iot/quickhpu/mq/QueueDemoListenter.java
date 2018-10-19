package com.iot.quickhpu.mq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

public class QueueDemoListenter implements MessageListener {
    @Override
    public void onMessage(Message msg  ) {
        try{
            System.out.println(new String(msg.getBody(),"UTF-8"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
