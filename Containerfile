FROM quay.io/quarkus/ubi-quarkus-mandrel:22.0.0.2-Final-java17 as build
WORKDIR /usr/src/app
COPY . /usr/src/app/
USER root
RUN ./gradlew clean
RUN chown -R quarkus /usr/src/app
USER quarkus
RUN ./gradlew buildNative && chmod +x /usr/src/app/application/build/native/nativeCompile/languages-api

FROM quay.io/quarkus/quarkus-distroless-image:1.0
COPY --from=build /usr/src/app/application/build/native/nativeCompile/languages-api /usr/local/bin/application
USER nonroot
CMD ["application"]
