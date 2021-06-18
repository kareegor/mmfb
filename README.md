# mmfb
multi micro frontends and backends
We are not providing a proper Readme now to build from scratch.
To do it better yourself we highly recommend you to join the sessions of bootcamp: https://beacloudpro.com/bootcamps/bootcamp_book

Youtube link of session 1: https://www.youtube.com/watch?v=Q8WsYyz1CEs&t=2192s

```bash
# below are some frequently required commands 
#add hostname in /etc/hosts file
127.0.1.1 www.mmfb.com
127.0.1.1 traefik.mmfb.com

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

#kubernetes commands from edge/kubernetes folder
#store certs
kubectl create configmap config-data --from-file=traefik/traefik.yml,certs/mmfb-cert.pem,certs/mmfb-key.pem
kubectl describe configmaps config-data
kubectl apply -f cert-secret.yaml
#create custom resource definition for traefik ingress controlling
kubectl apply -f 1-traefik-crd.yaml
#deploy Traefik with ServiceAccount, Traefik Admin Service and IngressRoute
kubectl apply -f 2-traefik-deployment.yaml
#Authserver deployment
kubectl apply -f 3-keyclaok-authserver.yaml

```
