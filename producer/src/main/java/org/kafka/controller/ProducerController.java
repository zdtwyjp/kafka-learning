package org.kafka.controller;

import org.kafka.service.KafkaProduceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/producer")
public class ProducerController {
    private static Logger logger = LoggerFactory.getLogger(ProducerController.class);

    @Autowired
    private KafkaProduceService kafkaProduceService;

    @RequestMapping("add")
    @ResponseBody
    public String add(String msg) {
        if (msg == null || msg.trim().length() == 0) {
            return "msg is null.";
        }
        kafkaProduceService.produceMsgToKafka(KafkaProduceService.TOPIC, msg);
        logger.info("add msg:{}", msg);
        return "add success.";
    }

}
