package org.kafka.controller;

import org.kafka.service.KafkaConsumerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {
    private static Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @RequestMapping("read")
    @ResponseBody
    public String read(String msg) {
        return kafkaConsumerService.getKafkaLogData();
    }

}
