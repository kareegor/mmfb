#!/bin/sh

echo "STARTING FRONTEND2... "
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.kareegar.frontend2.main.Frontend2Application"  "$@"
