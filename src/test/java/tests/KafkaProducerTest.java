package tests;

import base.KafkaTestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Kafka Тестирование")
@Feature("Producer")
public class KafkaProducerTest extends KafkaTestBase {

    @Test
    @Story("Создание producer")
    @Severity(SeverityLevel.MINOR)
    void testProducerCreation() {
        assertNotNull(producer);
    }
}