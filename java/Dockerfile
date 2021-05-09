# Copyright 2021 Amazon.com, Inc. or its affiliates. All Rights Reserved.
# Permission is hereby granted, free of charge, to any person obtaining a copy of this
# software and associated documentation files (the "Software"), to deal in the Software
# without restriction, including without limitation the rights to use, copy, modify,
# merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
# permit persons to whom the Software is furnished to do so.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
# INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
# PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
# HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
# OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
# SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

ARG GIT_REPO_DIR=DemoRepo

FROM amazon/aws-cli:latest AS fetcher
ARG GIT_URL=https://git-codecommit.us-east-1.amazonaws.com/v1/repos/DemoRepo

RUN yum install -y git
RUN mkdir -p /home/demo && \
    cd /home/demo && \
    git config --global credential.helper '!aws codecommit credential-helper $@' && \
    git config --global credential.UseHttpPath true && \
    git clone ${GIT_URL}


FROM alpine:3.13.0@sha256:d0710affa17fad5f466a70159cc458227bd25d4afb39514ef662ead3e6c99515 AS builder
ENV JAVA_HOME /opt/jdk
ENV PATH $JAVA_HOME/bin:$PATH
ARG GIT_REPO_DIR

ADD https://corretto.aws/downloads/latest/amazon-corretto-15-x64-alpine-jdk.tar.gz $JAVA_HOME/amazon-corretto-15-x64-alpine-jdk.tar.gz

RUN tar --extract --file $JAVA_HOME/amazon-corretto-15-x64-alpine-jdk.tar.gz --directory "$JAVA_HOME" --strip-components 1; \
    rm $JAVA_HOME/amazon-corretto-15-x64-alpine-jdk.tar.gz;

RUN ["jlink", "--compress=2", \
    "--module-path", "/opt/jdk/jmods/", \
    "--add-modules", "java.base,java.logging", \
    "--no-header-files", "--no-man-pages", \
    "--output", "/netty-runtime"]

RUN apk update && \
    apk add maven && \
    rm -rf /var/cache/apk/*

RUN mkdir -p /home/demo && \
    cd /home/demo

COPY --from=fetcher /home/demo/${GIT_REPO_DIR} /home/demo/${GIT_REPO_DIR}

RUN cd /home/demo/${GIT_REPO_DIR}/java && \
    mvn clean package

FROM alpine:3.13.0@sha256:d0710affa17fad5f466a70159cc458227bd25d4afb39514ef662ead3e6c99515
ENV PATH=$PATH:/opt/jdk/bin
ARG GIT_REPO_DIR

RUN addgroup -S demogroup && \
    adduser -S demouser -G demogroup

USER demouser

COPY --from=builder /netty-runtime /opt/jdk
COPY --from=builder /home/demo/${GIT_REPO_DIR}/java/target/netty-example-1.0-SNAPSHOT.jar /opt/app/
EXPOSE 8090
ENTRYPOINT ["java", "-showversion", "-jar", "/opt/app/netty-example-1.0-SNAPSHOT.jar"]
