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

# Expor porta (Railway vai usar a variável PORT)
EXPOSE ${PORT:-8080}

# Variáveis de ambiente
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Executar (Railway passa a porta via variável PORT)
CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar app.jar"]