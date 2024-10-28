pipeline {
    agent any

    tools { 
        gradle 'my-gradle'  // Use your Gradle installation name here
    }
    environment {
        POSTGRES_LOGIN = credentials('postgresql')  // Ensure this credential is set up with PostgreSQL username and password
    }
    stages {

        stage('Build with Gradle') {
            steps {
                sh 'gradle --version'
                sh 'java -version'
                sh 'gradle clean build -x test'  // -x test ignores tests if they should be optional
            }
        }

        stage('Packaging/Pushing image') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t mydocker/springboot-jenkins .'
                    sh 'docker push mydocker/springboot-jenkins'
                }
            }
        }

        stage('Deploy PostgreSQL to DEV') {
            steps {
                echo 'Deploying and cleaning PostgreSQL'
                sh 'docker image pull postgres:15'
                sh 'docker network create dev || echo "this network exists"'
                sh 'docker container stop thongnguyen-postgres || echo "this container does not exist"'
                sh 'echo y | docker container prune'
                sh 'docker volume rm thongnguyen-postgres-data || echo "no volume"'

                sh "docker run --name thongnguyen-postgres --rm --network dev -v thongnguyen-postgres-data:/var/lib/postgresql/data -e POSTGRES_PASSWORD=${POSTGRES_LOGIN_PSW} -e POSTGRES_DB=db_example -d postgres:15"
                sh 'sleep 20'
                sh "docker exec -i thongnguyen-postgres psql -U postgres -d db_example -f script.sql"

            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning Spring Boot'
                sh 'docker image pull mydocker/springboot'
                sh 'docker container stop thongnguyen-springboot || echo "this container does not exist"'
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune'

                sh 'docker container run -d --rm --name thongnguyen-springboot -p 8081:8080 --network dev mydocker/springboot'
            }
        }
 
    }
    post {
        // Clean after build
        always {
            cleanWs()
        }
    }
}