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
        stage('Build and Push'){
            steps{
               echo 'Starting to build docker image'
                script {
                    sh 'docker build -t hello-world .'
                    sh 'docker tag hello-world devops0001.jfrog.io/devops0002/hello-world:${BUILD_NUMBER}'
                    rtServer (
                        id: 'devops0002',
                        url: 'http://devops0001.jfrog.io/artifactory',
                        credentialsId: 'devops0001.jfrog.io',
                    )
 
                    rtDockerPush(
                        serverId: 'devops0002',
                        image: '$ARTIFACTORY_DOCKER_REGISTRY' + '/hello-world:${BUILD_NUMBER}',
                        host: 'devops0001.jfrog.io',
                        targetRepo: 'docker-local',
                        properties: 'project-name=hello-world;status=stable',
                        buildName: 'hello-world',
                        buildNumber: '${BUILD_NUMBER}', 
                        javaArgs: '-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005'
                    )
                     rtPublishBuildInfo (
                       serverId: 'devops0002'
                    )
               }    
            }
        }
        // stage('Push Docker Image'){
        //     steps{
        //         script{
        //             sh 'docker push devops0001.jfrog.io/devops0002/hello-world:${BUILD_NUMBER}'
        //         }  
        //     }
        // }
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