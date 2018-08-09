# Demo Script

## Getting started

* Get Kubernetes by enabling it on Docker for Mac/Windows
* Get kubectl and helm

## The script

1. Create webapi as a pod
    1. kubectl apply -f WebApi/pod.yaml
    2. kubectl get pods
    3. kubectl describe pod webapi
    4. kubectl logs webapi
2. Expose WebApi via a load balancer
    1. kubectl apply -f WebApi/loadbalancer.yaml
    2. curl http://localhost:8080/api/values
3. Create WebApi as a deployment
    1. kubectl delete -f WebApi/pod.yaml
    2. kubectl apply -f WebApi/deployment.yaml
    3. kubectl describe WebApi/deployment.yaml
    4. kubectl get pods
    5. kubectl delete pod 
    6. curl http://localhost:8080/api/values
4. Update WebApi deployment
    1. Change ENVIRONMENT to Development
    2. kubectl apply -f WebApi/deployment.yaml
    3. kubectl rollout history deployment webapi
    4. kubectl rollout undo deployment webapi --to-revision=1
    5. Change BOOM to true
    6. kubectl apply -f WebApi/deployment.yaml
    7. curl http://localhost:8080/api/values
    8. kubectl rollout undo deployment webapi 
5. Add another app
    1. kubectl apply -f MyFunction/deployment.yaml
    2. curl http://localhost:8081/hello\?name=Ducas
6. Service Discovery
    1. curl http://localhost:8080/api/helloproxy
7. HPA
    1. kubectl get hpa
    2. ./deploy-metrics-server.sh
    3. cd load-tests; docker-compose up
    4. kubectl get events -w
8. Use ingress controller
    1. kubectl delete svc webapi; kubectl delete svc hello-function;
    2. kubectl apply -f WebApi/service.yaml; kubectl apply -f MyFunction/service.yaml
    3. ./deploy-nginx-ingress.sh
    4. kubectl apply -f ingress.yaml
    5. curl http://localtest.me/fn/hello\?name=Ducas
    6. curl http://localtest.me/wa/api/values
    7. curl http://localtest.me/wa/api/helloproxy


