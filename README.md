# mmfb - Multi Micro Frontends and Backends

We are not providing a proper Readme now to build from scratch.
There are total 5 sessions to build a Cloud Agnostic Microservice Ecosystem with Spring boot, Zuul2, Consul, Traefik, Keycloak, Kafka, Angular, Prometheus, Grafana which will work in single node mode, docker swarm mode and in kubernetes of any provider. 
You can get all sessions list here: https://www.linkedin.com/pulse/cloud-agnostic-microfrontends-microservices-architecture-kareegor 


```bash
# below are some frequently required commands 
#add hostname in /etc/hosts file
127.0.1.1 www.mmfb.com
127.0.1.1 traefik.mmfb.com
#add hostname in /etc/hosts file if local openshift
<clusterIP> www.mmfb.kareegor.com traefik.mmfb.kareegor.com
# If it's the firt install of mkcert, run and generate certificates using [mkcert](https://github.com/FiloSottile/mkcert) :
mkcert -install

# Generate certificate for domain "mmfb.com" and their sub-domains in ${projecthome}/edge/certs folder
mkcert -cert-file certs/mmfb-cert.pem -key-file certs/mmfb-key.pem "mmfb.kareegor.com" "*.mmfb.kareegor.com"

#create java truststore (Look for the rootCA.pem file in - mkcert -CAROOT)
keytool -import  -trustcacerts  -alias mmbf  -file rootCA.pem   -keystore trustStoreFile
#put the trustStoreFile in ${projecthome}/edge/certs folder
#edit each project jib/entrypoint.sh with 
-Djavax.net.ssl.trustStore=/etc/certs/trustStoreFile -Djavax.net.ssl.trustStorePassword=<password>

# Docker commands may need in Dev mode
docker volume rm $(docker volume ls -q)
docker stop $(docker ps -aq)
docker service rm <stackname>_<service_name> e.g.  docker service rm traefik_frontend2
export HASHED_PASSWORD=$(openssl passwd -apr1 $PASSWORD)
docker network create --driver=overlay traefik-public
docker stack deploy -c <docker-compose_file>.yml traefik
docker service logs <stack_servicename> -f
docker service rm <stack_servicename>
mvn clean install jib:dockerBuild
#to build a clustered keycloak
cd edge/ha-swarm/keycloak
docker build . -t kareegar/keycloak
#nodejs commands:
npm install -g @angular/cli
ng new <appname>


# install openshift in your laptop 
# - https://developers.redhat.com/products/codeready-containers/overview
# - https://www.youtube.com/watch?v=aolL9wXAya4
#kubernetes commands from edge/kubernetes folder
#store certs
kubectl create secret tls secret-tls --cert=certs/mmfb-cert.pem --key=certs/mmfb-key.pem
kubectl create configmap config-data --from-file=traefik/traefik.yml,certs/
mmfb-cert.pem,certs/mmfb-key.pem
kubectl create configmap truststore --from-file=certs/trustStoreFile

kubectl describe configmaps config-data

docker login -u kubeadmin -p $(oc whoami -t) https://io.mmfb.kareegor.com
docker image tag frontend1:latest io.mmfb.kareegor.com/mmfb-dev-1/frontend1:latest
docker image push io.mmfb.kareegor.com/mmfb-dev-1/frontend1:latest

```
