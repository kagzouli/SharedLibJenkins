import com.exakaconsulting.Maven

def call() {
    node {
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

      
}
