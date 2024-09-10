FROM quay.io/keycloak/keycloak:latest as builder

# Enable health and metrics support
ENV KC_HEALTH_ENABLED=true
ENV KC_METRICS_ENABLED=true

# Configure a database vendor
ENV KC_DB=postgres
ENV KC_DB_URL=jdbc:postgresql://localhost:5555/srv_keycloak
ENV KC_DB_USERNAME=srv_keycloak
ENV KC_DB_PASSWORD=secret
ENV KC_HOSTNAME=localhost
ENV KC_HTTP_ENABLED=true
ENV KC_HTTP_PORT=5055
ENV KC_HTTPS_PORT=5056
ENV KC_LOG=console
ENV KC_LOG_CONSOLE_COLOR=true
ENV KC_LOG_LEVEL=debug

WORKDIR /opt/keycloak
# for demonstration purposes only, please make sure to use proper certificates in production instead
# RUN keytool -genkeypair -storepass password -storetype PKCS12 -keyalg RSA -keysize 2048 -dname "CN=server" -alias server -ext "SAN:c=DNS:localhost,IP:127.0.0.1" -keystore conf/server.keystore
RUN /opt/keycloak/bin/kc.sh build

FROM quay.io/keycloak/keycloak:latest
COPY --from=builder /opt/keycloak/ /opt/keycloak/

LABEL maven.project.build.uuid="@project.build.uuid@"

# change these values to point to a running postgres instance
ENV KC_DB=postgres
ENV KC_DB_URL=jdbc:postgresql://localhost:5555/srv_keycloak
ENV KC_DB_USERNAME=srv_keycloak
ENV KC_DB_PASSWORD=secret
ENV KC_HOSTNAME=localhost
ENV KC_HTTP_ENABLED=true
ENV KC_HTTP_PORT=5055
ENV KC_HTTPS_PORT=5056
ENV KC_LOG=console
ENV KC_LOG_CONSOLE_COLOR=true
ENV KC_LOG_LEVEL=debug

ENTRYPOINT ["/opt/keycloak/bin/kc.sh", "start", "--optimized"]


#ENTRYPOINT ["/usr/bin/sh", "-c", "sleep 5000"]
# <cmd>start --log=console --log-level=trace --http-management-port=5057 --https-port=5056 -http-port=5055 --http-enabled=true</cmd>