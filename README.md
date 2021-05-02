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

```
