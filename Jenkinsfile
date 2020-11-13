pipeline{
    agent any
    environment {
		dockerHome= tool 'mymaven'
		mavenHome= tool 'mydocker'
		PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
	}
    stages{
        stage(Checkout){
          steps{
              sh 'mvn --version'
              sh 'docker version'
          }
        }
        stage('Build'){
            steps{
                sh 'mvn clean compile'
            }
        }
        stage('Test'){
            steps{
                echo 'Test Here'
            }
        }
        stage('Integration Test'){
            steps{
                echo 'Integration Tests'
            }
        }
        stage('Package'){
           steps{
                sh 'mvn package -DskipTests'
           }
        }
        stage('Build Image'){
            steps{
               echo 'Starting to build docker image'
                script {
                    def dockerfile = 'Dockerfile'
                    def customImage = docker.build('devops0001.jfrog.io/devops0002/hello-world:latest', "-f ${dockerfile} .")
               }    
            }
        }
        stage('Push Docker Image'){
            steps{
                script{
                    echo 'customImage'
                    sh 'docker push devops0001.jfrog.io/devops0002/hello-world'
                }  
            }
        }
    }
    post{
		always{
			echo 'I run always'
		}
		success{
			echo 'I run when you are successful'
		}
		failure{
			echo 'I run when failure'
		}
		changed{
			echo 'I run when u fail'
		}
	}
}