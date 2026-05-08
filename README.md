# Kafka-JUnit5-Testcontainers-Docker-Jenkins

## 📖 Описание

Проект для тестирования **Apache Kafka** с использованием **JUnit 5**, **Testcontainers** и **Jenkins**.

Демонстрирует:
- ✅ Producer и Consumer на Java
- ✅ Реальный Kafka в Docker (Testcontainers)
- ✅ End-to-end тесты сообщений
- ✅ Интеграцию с Allure отчетами
- ✅ Jenkins Pipeline

## 🛠 Технологии

| Технология | Назначение |
|------------|------------|
| Java 17 | Язык программирования |
| JUnit 5 | Фреймворк для тестирования |
| Testcontainers | Kafka в Docker контейнере |
| Apache Kafka | Брокер сообщений |
| Maven | Сборка проекта |
| Jenkins | CI/CD |
| Allure | Отчеты о тестировании |

## 🚀 Запуск тестов локально

```bash
mvn clean test

```
## Структура проекта

src/test/java/
├── base/KafkaTestBase.java
├── consumer/MessageConsumer.java
├── producer/MessageProducer.java
└── tests/
├── KafkaE2ETest.java
├── KafkaConsumerTest.java
└── KafkaProducerTest.java


## Allure отчет

В Jenkins после сборки доступен Allure отчет с графиками и шагами тестов.

Все тесты зеленые, CI/CD настроен, Allure отчеты генерируются.
