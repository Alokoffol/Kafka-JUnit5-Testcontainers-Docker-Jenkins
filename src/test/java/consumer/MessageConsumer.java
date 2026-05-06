package consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MessageConsumer {

    private final KafkaConsumer<String, String> consumer;

    public MessageConsumer(String bootstrapServers, String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("group.id", groupId);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        this.consumer = new KafkaConsumer<>(props);
    }

    public List<String> consume(String topic, int expectedMessages, int timeoutSeconds) {
        consumer.subscribe(List.of(topic));
        List<String> messages = new ArrayList<>();
        long timeout = System.currentTimeMillis() + (timeoutSeconds * 1000L);

        while (messages.size() < expectedMessages && System.currentTimeMillis() < timeout) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            for (ConsumerRecord<String, String> record : records) {
                messages.add(record.value());
            }
        }
        return messages;
    }

    public void close() {
        consumer.close();
    }
}