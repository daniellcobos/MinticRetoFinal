package co.edu.mintic.ciclo4.minticciclo4.kafka;

import co.edu.mintic.ciclo4.minticciclo4.model.Login;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class Producer {
    Logger logger = LoggerFactory.getLogger(Producer.class);
    String bootstrapServers = "localhost:9092";
    String Topic = "login_topic";
    Properties properties = new Properties();
    KafkaProducer<String,Login> producer;
    public Producer(){

        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,LoginSerializer.class.getName());
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
        this.producer = new KafkaProducer<String,Login>(properties);
    }




    public void run(Login message) {
        ProducerRecord<String, Login> record = new ProducerRecord<String,Login>(Topic,message);
        logger.info(message+ "Trata de entrar");
        producer.send(record);
        producer.flush();
    }
}
