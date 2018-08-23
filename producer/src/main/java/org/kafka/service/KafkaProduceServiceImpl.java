package org.kafka.service;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Service("kafkaProduceService")
public class KafkaProduceServiceImpl
		implements KafkaProduceService {
	private static Logger logger = LoggerFactory.getLogger(KafkaProduceService.class);
	private Properties props = new Properties();
	private KafkaProducer<String, String> producer;

	@PostConstruct
	public void init() {
		try {
			props.load(KafkaProduceService.class.getClassLoader().getResourceAsStream("kafkaProduce.properties"));
			producer = new KafkaProducer<String, String>(props);
		} catch (IOException e) {
			logger.error("读取配置文件[kafkaProduce.properties]失败。", e);
		}
	}
	
	/**
	 * 向Kafka发送消息
	 * @author:junping.yang
	 * @param topic
	 * @param value void   
	 * @throws
	 */
	@Override
	public void produceMsgToKafka(String topic, String value) {
		producer.send(new ProducerRecord<>(topic, value));
	}

}
