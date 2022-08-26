FROM quay.io/quarkus/ubi-quarkus-mandrel:22.0.0.2-Final-java17 as build
WORKDIR /usr/src/app
COPY . /usr/src/app/
USER root
RUN ./gradlew clean
RUN chown -R quarkus /usr/src/app
USER quarkus
RUN ./gradlew :application:clean :application:build -Dquarkus.package.type=native -x test

FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=build /usr/src/app/application/build/*-runner /usr/local/bin/application
USER nonroot
CMD ["application", "-Dquarkus.http.host=0.0.0.0"]
