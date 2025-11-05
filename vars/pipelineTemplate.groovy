import com.exakaconsulting.Maven

def call(Closure body) {

    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    def gitUrl = config.gitUrl
    def gitBranch = config.gitBranch
    def subRepository = config.subRepository

     if (!gitUrl) {
        error "Git URL must be specified!"
    }

    node {
        stage('Checkout') {
           

            git branch: gitBranch, url: gitUrl
        }

        stage('Build') {
            script {
                steps.dir(subRepository){
                    Maven.cmdInstall(this)
                }
            }
        }
    }
}
