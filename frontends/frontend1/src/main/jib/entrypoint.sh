#!/bin/sh

echo "STARTING FRONTEND1... "
exec java ${JAVA_OPTS} -noverify -XX:+AlwaysPreTouch -Djava.security.egd=file:/dev/./urandom -cp /app/resources/:/app/classes/:/app/libs/* "com.kareegar.frontend1.main.Frontend1Application"  "$@"
