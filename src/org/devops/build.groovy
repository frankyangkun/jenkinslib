package org.devops

//构建类型
def Build(buildType,buildShell){
    def buildTools = ["mvn":"M2","ant":"ANT","gradle":"GRADLE","npm":"NPM"]
    println("当前选择的构建类型为：${buildType}")
    buildHome = tool buildTools[buildType]
    sh "pwd"
    sh "mvn -v"
    sh "ls"
    //sh "${buildHome}/bin/${buildType} ${buildShell}"
    sh "${buildType} ${buildShell}"
}

//获取url中的项目名
def getProjectName(url){
    //http://131.10.11.94:31154/root/044-springboot-dubbo-ssm-parent.git
    splitStr1 = url.split('/')
    //splitStr2 = url.tokenize('.')
    
    println("getProjectName按/分割结果gitlab：${splitStr1}")
    //println(splitStr1) //用split分割的，打印的是内存地址，也可${}获取实际内容
    //println("项目名称：${splitStr1}[-1]") //无法从结果中进一步取值
    //println("项目名称：${splitStr1}[4]") //无法从结果中进一步取值
    println("项目名称："+splitStr1[-1])//只能这样写才可获取列表中的数据，-1表示最后一个
    return splitStr1[-1]
    //println("getProjectName按.分割结果gitlab：${splitStr2}")
   // println(splitStr2) //用tokensize分割的，打印的是实际内容
}
