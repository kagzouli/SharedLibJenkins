import com.exakaconsulting.Maven

def call(Map args = [:]) {

    def gitUrl = args.get('gitUrl', '')
    def gitBranch = args.get('gitBranch', 'main')
    def subRepository = args.get('subRepository', 'main')

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
