pipeline{
    agent any
    environment {
		dockerHome= tool 'mymaven'
		mavenHome= tool 'mydocker'
		registry = "devops0001.jfrog.io/devops0002"
        registryCredential = 'devops0001.jfrog.io'
		PATH = "$dockerHome/bin:$mavenHome/bin:$PATH"
		
	}
    stages{
        stage(Checkout){
          steps{
              //sh 'mvn --version'
              sh 'docker version'
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
                    //def dockerfile = 'Dockerfile'
	           def dockerImage = docker.build registry + "hello-world:$BUILD_NUMBER"
					
               }    
            }
        }
        stage('Push Docker Image'){
            steps{
                script{
                    echo 'customImage'
                    //sh 'docker tag hello-world:${BUILD_NUMBER} devops0001.jfrog.io/devops0002/hello-world'
                    //sh 'docker push devops0001.jfrog.io/devops0002/hello-world:${BUILD_NUMBER}'
			sh 'docker push devops0001.jfrog.io/devops0002/hello-world:${BUILD_NUMBER}'
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
