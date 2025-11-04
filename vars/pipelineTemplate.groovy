import com.exakaconsulting.Maven

def call() {
    node {
        stage('Checkout') {
            checkout scm
            sh "cd application/spring-java"

        }

        stage('Build') {
            script {
                Maven.cmdInstall(this)
            }
        }
    }
}
