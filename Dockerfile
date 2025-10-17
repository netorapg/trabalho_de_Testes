# Stage 1: Build
FROM maven:3.8.7-eclipse-temurin-17-alpine AS build

WORKDIR /build

# Copiar pom.xml e baixar dependências (cache)
COPY java-rest-api/pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fonte e buildar
COPY java-rest-api/src ./src
RUN mvn clean package -DskipTests

# Stage 2: Runtime
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copiar JAR da stage anterior
COPY --from=build /build/target/*.jar app.jar

# Expor porta
EXPOSE 8080

# Variáveis de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Healthcheck
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8080/api/users || exit 1

# Executar
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]