pipeline {
    agent any

    tools { 
        gradle 'my-gradle'  // Tên cấu hình Gradle đã cài đặt trên Jenkins
    }
    environment {
        POSTGRES_LOGIN = credentials('postgresql')  // Đảm bảo đã thiết lập credential cho PostgreSQL
    }
    stages {

        stage('Build with Gradle') {
            steps {
                sh 'gradle --version'
                sh 'java -version'
                sh './gradlew clean bootJar -x test'  // Build JAR file mà không chạy tests
            }
        }

        stage('Packaging/Pushing image') {
            steps {
                withDockerRegistry(credentialsId: 'dockerhub', url: 'https://index.docker.io/v1/') {
                    sh 'docker build -t thongnguyen120600/springboot-jenkins .'  // Build image từ Dockerfile trong thư mục gốc
                    sh 'docker push thongnguyen120600/springboot-jenkins'
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
                
            }
        }

        stage('Deploy Spring Boot to DEV') {
            steps {
                echo 'Deploying and cleaning Spring Boot'
                sh 'docker image pull thongnguyen120600/springboot-jenkins'  // Pull image từ Docker Hub sau khi đã push
                sh 'docker container stop thongnguyen-springboot || echo "this container does not exist"'
                sh 'docker network create dev || echo "this network exists"'
                sh 'echo y | docker container prune'

                sh 'docker container run -d --rm --name thongnguyen-springboot -p 8081:8080 --network dev thongnguyen120600/springboot-jenkins'
            }
        }
    }

    post {
        // Clean sau khi build
        always {
            cleanWs()
        }
    }
}
