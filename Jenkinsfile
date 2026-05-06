pipeline {
    agent any

    tools {
        maven 'Maven-3.9.0'
        jdk 'JDK-17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo '✅ Код скачан'
            }
        }

        stage('Run Kafka Tests') {
            steps {
                bat 'mvn clean test'
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