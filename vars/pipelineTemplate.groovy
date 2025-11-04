import com.exakaconsulting.Maven

def call(Map args = [:]) {

    def gitUrl = args.get('gitUrl', '')
    def gitBranch = args.get('gitBranch', 'main')

     if (!gitUrl) {
        error "Git URL must be specified!"
    }

    node {
        stage('Checkout') {
           

            git branch: gitBranch, url: gitUrl
            steps.bat "cd application/spring-java"

        }

        stage('Build') {
            script {
                Maven.cmdInstall(this)
            }
        }
    }
}
