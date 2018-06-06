# basic 根据自己需要修改Dockerfile
# 命令范例 docker build -t jdkhome/blzo:0.0.1 ./

FROM daocloud.io/library/java:openjdk-8u40
MAINTAINER main@jdkhome.com

# 拷贝可执行程序
COPY build/libs /var/basic

# 端口
EXPOSE 8080

CMD ["java", "-jar", "-Dfile.encoding=UTF8", "-Duser.timezone=GMT+08", "-Dfastjson.parser.autoTypeSupport=true", "/var/basic/basic.jar"]

# docker run -d --name "blzo" -e SPRING_PROFILES_ACTIVE=develop -p 8080:8080 -v /data/blzo:/tmp -v /data/blzo/logs:/tmp/logs jdkhome/blzo:latest
