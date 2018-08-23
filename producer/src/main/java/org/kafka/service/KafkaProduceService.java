package org.kafka.service;

public interface KafkaProduceService {
    public static final String TOPIC = "test";

    /**
     * 向Kafka发送消息
     *
     * @param topic
     * @param value void
     * @throws
     * @author:junping.yang
     */
    public void produceMsgToKafka(String topic, String value);

}
