package com.example.javaexamples.kafka;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProducerDemo {
    public static void main(String[] args) {

        String[] messages = new String[] {
            "HELLO WORLD",
        };

        String bootstrapServers = "127.0.0.1:9092";

        // create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create producer records
        for (int i=0 ; i<4 ; i++) {
            String message = messages[i];
            ProducerRecord<String, String> producerRecord =
                    new ProducerRecord<>("QUEUE_NAME", message);

            System.out.println(message);

            // send data - asynchronous
            producer.send(producerRecord);
        }

        // flush data - synchronous
        producer.flush();

        // flush and close producer
        producer.close();
    }
}
