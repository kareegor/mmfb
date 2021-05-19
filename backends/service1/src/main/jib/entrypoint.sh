#!/bin/sh

echo "STARTING Service1... "
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -Djavax.net.ssl.trustStore=/etc/certs/trustStoreFile -Djavax.net.ssl.trustStorePassword=123456 -cp /app/resources/:/app/classes/:/app/libs/* "com.kareegar.mmfb.service1.Service1"  "$@"
