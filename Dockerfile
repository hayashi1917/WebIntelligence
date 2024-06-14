FROM amazoncorretto:17
WORKDIR /usr/src/app
RUN yum update -y && yum install -y procps
CMD [ "java --version" ]
SHELL [ "/bin/bash", "-c" ]