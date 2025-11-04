import com.exakaconsulting.Maven

def call() {
    return pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    checkout scm
                    sh "cd application/spring-java"
                }
            }

            stage('Build') {
                steps{
                    script {
                        Maven.cmdInstall(this)
                    }
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
