package org.devops

//封装http请求
def HttpReq(reqType,reqUrl,reqBody){
    def apiServer = "https://49.235.150.129:6443/apis/apps/v1"
    withCredentials([string(credentialsId: 'kubernetes-token',variable: 'kubernetesToken')]){
      result = httpRequest customHeaders: [[maskValue: true, name: 'Authoriation', value: "Bearer ${kubernetesToken}"],[maskValue: false, name: 'Content-Type', value: 'application/yaml'],[maskValue: false, name: 'Accept',value:'application/yaml']],
               httpMode: reqType,
               //contentType: "TEXT_HTML",
               consoleLogResponseBody: true,
               ignoreSslErrors: true,
               requestBody: reqBody,
               url: "${apiServer}/${reqUrl}"
    }
    return result
}

//获取Deployment
def GetDeployment(nameSpace,deployName){
    apiUrl = "namespaces/${nameSpace}/deployments/${deployName}"
    response = HttpReq('GET',apiUrl,'')

    //def datas = readYaml text: """${response.content}"""
    //println(datas)
    return response
}

//更新Deployment
def UpdateDeployment(nameSpace,deployName,deployBody){
    apiUrl = "namespaces/${nameSpace}/deployments/${deployName}"
    response = HttpReq('PUT',apiUrl,deployBody)
    println(response)
}
