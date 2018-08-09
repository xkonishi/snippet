FROM localhost:5000/base_images/centos:7.3.1611

MAINTAINER XXXXX

ENV JDK_RPM_NAME=jdk-8u121-linux-x64.rpm \
    JAVA_HOME=/usr/java/jdk1.8.0_121 \
    TOMCAT7_VERSION=7.0.69 \
    CATALINA_HOME=/usr/local/tomcat \
    PROG_NAME=progName \
    LIB_ZIP_NAME=redistribution_prohibited_lib.zip \
    TOMCAT_PORT=8080

ENV http_proxy=http://xxxx:9125 \
    https_proxy=http://xxxx:9125 \
    no_proxy=localhost,127.0.0.0/8,::1,10.*.*.*


WORKDIR /tmp/tomcat_build

COPY install_files/others/$JDK_RPM_NAME ./

RUN yum -y --setopt=tsflags=nodocs update && \
    yum -y --setopt=tsflags=nodocs localinstall ./$JDK_RPM_NAME && \
    yum clean all

#COPY install_files/apache-tomcat-$TOMCAT7_VERSION.tar.gz ./

RUN yum -y --setopt=tsflags=nodocs install wget && \
    wget  https://archive.apache.org/dist/tomcat/tomcat-7/v$TOMCAT7_VERSION/bin/apache-tomcat-$TOMCAT7_VERSION.tar.gz && \
    tar xzvf apache-tomcat-$TOMCAT7_VERSION.tar.gz -C /usr/local/ && \
    useradd -s /sbin/nologin tomcat && \
    chown -R tomcat:tomcat /usr/local/apache-tomcat-$TOMCAT7_VERSION && \
    ln -s /usr/local/apache-tomcat-$TOMCAT7_VERSION $CATALINA_HOME

RUN yum -y --setopt=tsflags=nodocs install sudo && \
    sudo -u tomcat echo "#!/bin/bash" >> $CATALINA_HOME/bin/setenv.sh && \
    sudo -u tomcat echo "export JAVA_HOME=$JAVA_HOME" >> $CATALINA_HOME/bin/setenv.sh && \
    sudo -u tomcat echo 'export JRE_HOME=$JAVA_HOME/jre' >> $CATALINA_HOME/bin/setenv.sh

COPY install_files/prog/$PROGN_NAME.war ./

RUN sudo -u tomcat mkdir $CATALINA_HOME/webapps/$PROG_NAME && \
    sudo -u tomcat cp -r ./$PROG_NAME.war $CATALINA_HOME/webapps/$PROG_NAME && \
    cd $CATALINA_HOME/webapps/$PROG_NAME && \
    sudo -u tomcat jar -xvf $CATALINA_HOME/webapps/$PROG_NAME/$PROG_NAME.war

COPY install_files/progner/$LIB_ZIP_NAME ./

RUN yum -y --setopt=tsflags=nodocs install unzip && \
    unzip ./$LIB_ZIP_NAME -d ./ && \
    chown tomcat:tomcat ./*.jar && \
    mv -f ./*.jar $CATALINA_HOME/webapps/prognerAPTemplateDesktopLikeWeb/WEB-INF/lib

RUN localedef -i ja_JP -c -f UTF-8 -A /usr/share/locale/locale.alias ja_JP.UTF-8
ENV LANG=ja_JP.UTF-8

EXPOSE $TOMCAT_PORT


CMD sudo -u tomcat $CATALINA_HOME/bin/startup.sh && \
    wait && \
    tail -f $CATALINA_HOME/logs/catalina.out

