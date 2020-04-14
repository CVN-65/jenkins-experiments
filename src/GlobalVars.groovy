class GlobalVars {
    def getPodTemplate() {
        def pd = [
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
                    
                ]
            ]
        ]
        return pd
    }
}