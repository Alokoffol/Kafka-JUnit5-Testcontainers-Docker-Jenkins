package base;

import consumer.MessageConsumer;
import producer.MessageProducer;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
public class KafkaTestBase {

    @Container
    protected static final KafkaContainer kafka = new KafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.5.0")
    );

    protected MessageProducer producer;
    protected MessageConsumer consumer;
    protected String topic;

    @BeforeEach
    @Step("Подготовка Kafka окружения")
    void setUp() {
        String bootstrapServers = kafka.getBootstrapServers();
        producer = new MessageProducer(bootstrapServers);
        consumer = new MessageConsumer(bootstrapServers, "test-group");
        topic = "test-topic-" + System.currentTimeMillis();

        System.out.println("✅ Kafka контейнер запущен: " + bootstrapServers);
        System.out.println("📨 Топик: " + topic);
    }

    @AfterEach
    @Step("Закрытие producer и consumer")
    void tearDown() {
        if (producer != null) {
            producer.close();
            System.out.println("🔒 Producer закрыт");
        }
        if (consumer != null) {
            consumer.close();
            System.out.println("🔒 Consumer закрыт");
        }
    }
}