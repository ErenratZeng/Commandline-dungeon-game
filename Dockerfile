FROM oraclelinux:8
RUN yum install -y java-17-openjdk-devel && yum clean all

WORKDIR /app

COPY gradlew gradlew.bat /app/
COPY gradle /app/gradle
COPY build.gradle settings.gradle /app/

COPY src /app/src

RUN sed -i 's/\r$//' ./gradlew
RUN chmod +x ./gradlew

RUN yum update -y && yum install -y findutils
RUN ./gradlew build

COPY gson-2.11.0.jar /app/build/libs
CMD ["java", "-jar", "/app/build/libs/comp2120-fri10_a3_c.jar"]
