package org.devops

//封装HTTP请求
def HttpReq(reqType,reqUrl,reqBody){
    def gitServer = "http://131.10.11.94:31154/api/v4"
    withCredentials([string(credentialsId: 'gitlab-token',variable: 'gitlabToken')]){
      result = httpRequest customHeaders: [[maskValue: true, name: 'PRIVATE-TOKEN', value: "${gitlabToken}"]],
               httpMode: reqType,
               contentType: "APPLICATION_JSON",
               consoleLogResponseBody: true,
               ignoreSslErrors: true,
               requestBody: reqBody,
               url: "${gitServer}/${reqUrl}"
    }
    return result
}

//更改提交状态
def ChangeCommitStatus(projectId,commitSha,status){
    commitApi = "projects/${projectId}/statuses/${commitSha}?state=${status}"
    response = HttpReq('POST',commitApi,'')
    println(response)
    return response
}

//新建仓库文件（在k8s部署的应用的yaml部署文件）
def CreateRepoFile(projectId,filePath,fileContent){
    apiUrl = "projects/${projectId}/repository/files/${filePath}"
    reqBody = """{"branch": "master", "content": ${fileContent}, "commit_message": "create a new file"}"""
    response = HttpReq('POST',apiUrl,reqBody)
    println(response)
}
