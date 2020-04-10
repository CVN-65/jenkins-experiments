def buildNumber = env.BUILD_NUMBER as int
if (buildNumber > 1) milestone(buildNumber - 1)
milestone(buildNumber)

def podLabel = "jnlp-pod-template"
def containerName = "jnlp"

def pt = 
[
    label: podLabel,
    cloud: 'openshift',
    namespace: 'ops-datalab-dev-axa-it',
    nodeUsageMode: 'EXCLUSIVE',
    slaveConnectTimeout: 100,
    containers: [
        [
            name: containerName,
            image: 'devallsystem/test:1.0.0',
            workingDir: '/home/jenkins',
            args: '${computer.jnlpmac} ${computer.name}',
            alwaysPullImage: true,
            ttyEnabled: false,
            resourceRequestCpu: '1000m',
            resourceRequestMemory: '2Gi',
            resourceLimitCpu: '2000m',
            resourceLimitMemory: '2.5Gi',
            livenessProbe: containerLivenessProbe(
                initialDelaySeconds: 0,
                timeoutSeconds: 0,
                failureThreshold:0,
                periodSeconds: 0,
                successThreshold: 0
            ),
            envVars: [
                envVar(
                    key: 'no_proxy',
                    value: 'openpaas-jenkins-deploy,172.30.0.1,*.svc.cluster.local,openpaas-jenkins-deploy.ops-datalab-dev-axa-it.svc.cluster.local'
                ),
                [
                    neme: 'test',
                    value: 'prova'
                ]
                // [
                //     symbol: 'envVar',
                //     klass: 'TemplateEnvVar',
                //     arguments: [
                //         key: "HTTPS_PROXY",
                //         value: "http://http-proxy-internal.ops-datalab-dev-axa-it.svc.cluster.local:8080"
                //     ]
                // ]
            ]
        ]
    ]
    // ,
    // volumes: [
    //     secretVolume(
    //         secretName: 'infra-test-aws',
    //         mountPath: '/home/jenkins/.aws/'
    //     )
    // ]
]
podTemplate(
    // pt
    cloud: 'openshift',
    yaml: """
apiVersion: "v1"
kind: "Pod"
metadata:
  name: "jnlp-pod-template"
spec:
  containers:
  - args:
    name: "jnlp"
    image: "devallsystem/test:1.0.0"
    imagePullPolicy: "Always"
    tty: false
    resources:
      limits:
        memory: "2.5Gi"
        cpu: "2000m"
      requests:
        memory: "2Gi"
        cpu: "1000m"
  restartPolicy: "Never"
    """
) {
    node(podLabel) {
        stage('Test asw s3 template') {
            container(containerName) {
                stage('Dependencies') {
                    sh "ls -la"
                }
            }
        }
    }

}