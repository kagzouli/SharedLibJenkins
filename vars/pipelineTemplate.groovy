import com.exakaconsulting.Maven

def call(Closure body) {

    def config = [:]                     // create empty map
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()      // 👈 this line is essential — it executes the closure

    def gitUrl = config.gitUrl
    def gitBranch = config.gitBranch
    def subRepository = config.subRepository

     if (!gitUrl) {
        error "Git URL must be specified!"
    }

    if (!gitBranch){
        error "Git Branch must be specified"
    }

    if (!subRepository){
        error "SubRepository must be specified"
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
