pipeline {
    agent any

    tools {
        maven 'Maven-3.9.0'
        jdk 'JDK-17'
        allure 'Allure'  // ← добавь эту строку
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo '✅ Код скачан'
            }
        }

        stage('Run Tests') {
            steps {
                bat 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                allure([
                    includeProperties: false,
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            echo '📊 Тесты завершены'
        }
        success {
            echo '✅ Все тесты прошли успешно!'
        }
        failure {
            echo '❌ Есть упавшие тесты'
        }
    }
}