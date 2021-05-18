# mmfb
multi micro frontends and backends


```bash
#add hostname in /etc/hosts file
127.0.1.1 www.mmfb.com
127.0.1.1 traefik.mmfb.com

# If it's the firt install of mkcert, run and generate certificates using [mkcert](https://github.com/FiloSottile/mkcert) :
mkcert -install

# Generate certificate for domain "mmfb.com" and their sub-domains in ${projecthome}/edge/certs folder
mkcert -cert-file certs/mmfb-cert.pem -key-file certs/mmfb-key.pem "mmfb.com" "*.mmfb.com"

#create java truststore (Look for the rootCA.pem file in - mkcert -CAROOT)
keytool -import  -trustcacerts  -alias mmbf  -file rootCA.pem   -keystore trustStoreFile
#put the trustStoreFile in ${projecthome}/edge/certs folder
#edit each project jib/entrypoint.sh with 
-Djavax.net.ssl.trustStore=/etc/certs/trustStoreFile -Djavax.net.ssl.trustStorePassword=<password>

# Docker commands may need in Dev mode
docker volume rm $(docker volume ls -q)
docker stop $(docker ps -aq)
docker service rm <stackname>_<service_name> e.g.  docker service rm traefik_frontend2
docker stack deploy -c <docker-compose_file>.yml traefik
docker service logs <stack_servicename> -f
mvn clean install jib:dockerBuild

#nodejs commands:
npm install -g @angular/cli
ng new <appname>
```
