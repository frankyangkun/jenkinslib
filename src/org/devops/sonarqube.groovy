package org.devops

//scan
def SonarScan(sonarServer,projectName,projectDsec,projectPath){
    //定义服务器列表，如测试环境，开发环境，可切换，我这里暂时只有sonarqube-server 1个
    def serverlist = ["test":"sonarqube-server","prod":"sonarqube-server-prod"]
    
    //和jenkins的sonarqube插件里配置的名称一致
    withSonarQubeEnv("${serverlist[sonarServer]}"){ 
        def sonarHome= "/usr/local/sonar-scanner-4.7.0.2747-linux"
        //def sonarServer = "http://131.10.11.94:31262" //使用插件后，sh里已经注释掉，它就不生效了
        def sonarDate = sh returnStdout: true, script: 'date +%Y%m%d%H%M%S'
        sonarDate = sonarDate - "\n"

        //使用插件后，sh去掉了3行内容
        //${sonarHome}/bin/sonar-scanner -Dsonar.host.url=${sonarServer} \
        //-Dsonar.login=admin \
        //-Dsonar.password=12345678 \

        sh """
            ${sonarHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
            -Dsonar.projectName=${projectName} \
            -Dsonar.projectVersion=${sonarDate} \
            -Dsonar.ws.timeout=30 \
            -Dsonar.projectDescription=${projectDsec} \
            -Dsonar.links.homepage=http://www.baidu.com \
            -Dsonar.sources=${projectPath} \
            -Dsonar.sourceEncoding=UTF-8 \
            -Dsonar.java.binaries=target/classes \
            -Dsonar.java.test.binaries=target/test-classes \
            -Dsonar.java.surefire.report=target/surefire-reports
        """
    }
}
