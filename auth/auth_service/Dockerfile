FROM metamagicglobal/tomcat:k8

RUN apt-get -y update
RUN apt-get install -y zip 

COPY target/auth-0.0.1-SNAPSHOT.war /Home/metaMagic/Softwares

RUN mkdir /Home/metaMagic/Softwares/tc9/webapps/auth-0.0.1-SNAPSHOT
RUN unzip -qq /Home/metaMagic/Softwares/auth-0.0.1-SNAPSHOT.war -d /Home/metaMagic/Softwares/tc9/webapps/authms
RUN sed -i "s/8080/8082/g" /Home/metaMagic/Softwares/tc9/conf/server.xml 
RUN sed -i "s/8443/9043/g" /Home/metaMagic/Softwares/tc9/conf/server.xml 

EXPOSE 8082

# when the container launches
CMD ["supervisord","-c", "/etc/supervisor/conf.d/tomcat.conf"]