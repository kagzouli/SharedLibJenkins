import com.exakaconsulting.Maven

def call(String gitUrl = '', String gitBranch = 'main') {
    node {
        stage('Checkout') {
            if (!gitUrl) {
                error "Git URL must be specified!"
            }

            git branch: gitBranch, url: gitUrl
            sh "cd application/spring-java"

        }

        stage('Build') {
            script {
                Maven.cmdInstall(this)
            }
        }
    }
}
