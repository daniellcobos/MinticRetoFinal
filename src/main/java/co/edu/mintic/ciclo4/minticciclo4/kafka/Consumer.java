package co.edu.mintic.ciclo4.minticciclo4.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class Consumer {
    public Consumer(){

    }

    private void run(){
        Logger logger = LoggerFactory.getLogger(Consumer.class);
        String bootstrapServers = "localhost:9092";
        String groupId = "javaApp";
        String Topic = "login_topic";
        CountDownLatch latch = new CountDownLatch(1);

        Runnable consumerRunnable = new ConsumerRunnable(latch,Topic,bootstrapServers,groupId);
        Thread consumerThread = new Thread(consumerRunnable);
        consumerThread.start();

        //shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(
                () -> {logger.info("Caught Shutdown hook");
                    ((ConsumerRunnable)consumerRunnable).shutdown();
                    try{
                        latch.await();}
                    catch (InterruptedException e) {
                        logger.info("Application finished" + e);
                    }
                    finally {
                        logger.info("Application exited");
                    }
                }
        ));
        try{
            latch.await();}
        catch (InterruptedException e) {
            logger.info("Exception" + e);
        }
        finally {
            logger.info("Closing");
        }

    }

    public class ConsumerRunnable implements Runnable{

        private CountDownLatch latch;
        private KafkaConsumer<String,String> consumer;
        private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class);;

        public ConsumerRunnable(CountDownLatch latch, String Topic, String bootstrapServers, String groupId){
            this.latch = latch;
            Properties properties = new Properties();
            properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServers);
            properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class.getName());
            properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,groupId);
            properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");
            consumer =  new KafkaConsumer<String,String>(properties);
            consumer.subscribe(Collections.singleton(Topic));
        }

        @Override
        public void run(){
            try{
                while(true){
                    ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(1000));
                    for (ConsumerRecord<String,String> record: records){
                        logger.info("Key: " + record.key() + ", Value: " + record.value());
                        logger.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                    }
                }
            } catch (WakeupException e){
                logger.info("despierta");
            } finally {
                consumer.close();
                latch.countDown();
            }
        }
        public void shutdown(){
            consumer.wakeup();
        }


    }
}
