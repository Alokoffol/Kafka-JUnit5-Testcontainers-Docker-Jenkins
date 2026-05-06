package tests;

import base.KafkaTestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Kafka Тестирование")
@Feature("End-to-End сообщения")
public class KafkaE2ETest extends KafkaTestBase {

    @Test
    @Story("Отправка и получение одного сообщения")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверяет, что producer отправляет, а consumer получает одно сообщение")
    void testSendAndReceiveMessage() throws Exception {
        String expectedMessage = "Hello, Kafka!";

        Allure.addAttachment("Отправляемое сообщение", expectedMessage);

        producer.send(topic, "key1", expectedMessage).get();
        List<String> messages = consumer.consume(topic, 1, 5);

        assertEquals(1, messages.size());
        assertEquals(expectedMessage, messages.get(0));

        Allure.addAttachment("Полученное сообщение", messages.get(0));
    }

    @Test
    @Story("Отправка и получение нескольких сообщений")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверяет, что все отправленные сообщения доходят до consumer")
    void testMultipleMessages() throws Exception {
        String[] expectedMessages = {"Message 1", "Message 2", "Message 3"};

        Allure.addAttachment("Отправляемые сообщения",
                String.join(", ", expectedMessages));

        for (int i = 0; i < expectedMessages.length; i++) {
            producer.send(topic, "key" + i, expectedMessages[i]).get();
        }

        List<String> messages = consumer.consume(topic, 3, 10);

        assertEquals(3, messages.size());
        assertArrayEquals(expectedMessages, messages.toArray());
    }
}