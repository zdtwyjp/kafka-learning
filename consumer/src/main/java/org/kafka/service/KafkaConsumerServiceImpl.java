package org.kafka.service;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

@Service("kafkaConsumerService")
public class KafkaConsumerServiceImpl implements KafkaConsumerService{
    private static Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private Properties props = new Properties();
    private KafkaConsumer<String, String> consumer;

    @PostConstruct
    public void init() {
        try {
            props.load(KafkaConsumerService.class.getClassLoader().getResourceAsStream("kafkaConsumer.properties"));
            consumer = new KafkaConsumer<String, String>(props);
            consumer.subscribe(Arrays.asList(KafkaConsumerService.TOPIC));
        } catch (IOException e) {
            logger.error("读取配置文件[kafkaConsumer.properties]失败。", e);
        }
    }

    @Override
    public String getKafkaLogData() {
        logger.info("do getKafkaLogData begin... ");
        ConsumerRecords<String, String> records = consumer.poll(100);
        logger.info("records > " + records.count());
        StringBuffer str = new StringBuffer();
        for (ConsumerRecord<String, String> record: records) {
            JSONObject recordObj = JSONObject.parseObject(record.value());
            str.append(recordObj.toJSONString());
        }
        logger.info("do getKafkaLogData end. ");
        return str.toString();
    }

}
