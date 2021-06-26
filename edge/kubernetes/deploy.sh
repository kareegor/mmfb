#!/bin/bash

## Templating yaml files with shell sed command, only for namespace now
## to change the domains according to your domain please create some SED replace variable
export NEW_NS='namespace: mmfb-dev-1' 
export OLD_NS='namespace: mmfb'
export SED_CMD=s/${OLD_NS}/${NEW_NS}/g

echo "Creating the TLS secret"
kubectl create secret tls secret-tls --cert=certs/mmfb-cert.pem --key=certs/mmfb-key.pem
echo "Creating the Config Data for Traefik Ingress Controller"
kubectl create configmap config-data --from-file=traefik/traefik.yml,certs/mmfb-cert.pem,certs/mmfb-key.pem
kubectl create configmap truststore --from-file=certs/trustStoreFile
echo "creating custom resource definition for traefik ingress controlling"
sed "$SED_CMD" 1-traefik-crd.yaml | kubectl apply -f -
echo "deploying traefik service, deployment and ingressroute"
sed "$SED_CMD" 2-traefik-deployment.yaml | kubectl apply -f -
echo "deploying keyclaok server with H2 DB"
sed "$SED_CMD" 3-keycloak-authserver.yaml | kubectl apply -f -
echo "claiming volume for consul"
sed "$SED_CMD" 4-consul-volumeclaim.yaml | kubectl apply -f -
echo "deploying consul and its replicas"
sed "$SED_CMD" 5-consul-deployment.yaml | kubectl apply -f -
echo "Frontends and API Gateway"
sed "$SED_CMD" 6-frontend1-deployment.yaml | kubectl apply -f -
echo "Frontends and API Gateway"
sed "$SED_CMD" 7-frontend2-deployment.yaml | kubectl apply -f -
echo "Kafka Deployment"
sed "$SED_CMD" 8-kafka0-deployment.yaml | kubectl apply -f -
echo "Microservices Deployment"
sed "$SED_CMD" 9-services-deployment.yaml | kubectl apply -f -

