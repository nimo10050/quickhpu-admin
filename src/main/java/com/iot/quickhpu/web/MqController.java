package com.iot.quickhpu.web;

import com.iot.quickhpu.common.HPUResult;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MQ 生产者
 */
@Controller
@RequestMapping("mq")
public class MqController {

    @Autowired
    private AmqpTemplate mqTemplate;

    @RequestMapping("/send")
    @ResponseBody
    public HPUResult send() throws Exception{
        mqTemplate.convertAndSend("hello", "hello");
        return HPUResult.ok();
    }

}
