import com.exakaconsulting.Maven

def call(Map config = [:]) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                    sh "cd application/spring-java"
                }
            }

            stage('Build') {
                steps {
                    Maven.cmdInstall()
                }
            }
        }

        post {
            success {
                echo 'Pipeline completed successfully!'
            }
            failure {
                echo 'Pipeline failed!'
            }
        }
    }
}
