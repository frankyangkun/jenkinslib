@Library('jenkinslib') _

def tools = new org.devops.tools()

pipeline {
    agent any
    
    stages {
        stage('Hello') {
            steps {
                echo 'Hello World'
                script{
                    print("代码扫描")
                    tools.PrintMes("******this is my lib.******")
                }
            }
        }
    }
}
