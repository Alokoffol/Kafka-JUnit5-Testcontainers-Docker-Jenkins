package tests;

import base.KafkaTestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Kafka Тестирование")
@Feature("Consumer")
public class KafkaConsumerTest extends KafkaTestBase {

    @Test
    @Story("Создание consumer")
    @Severity(SeverityLevel.MINOR)
    void testConsumerCreation() {
        assertNotNull(consumer);
    }
}