#!/bin/sh

echo "STARTING FRONTEND3... "
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -Djavax.net.ssl.trustStore=/etc/certs/trustStoreFile -Djavax.net.ssl.trustStorePassword=123456 -cp /app/resources/:/app/classes/:/app/libs/* "com.kareegor.mmfb.frontend3.Frontend3Application"  "$@"
