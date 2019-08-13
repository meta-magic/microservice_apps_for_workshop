FROM metamagicglobal/apache:jdk8

#RUN rm -rf /etc/apache2/apache2.conf

RUN chmod -R 777 /var/www/html/
RUN mkdir /var/www/html/mainshell/
ADD dist/mainshell/ /var/www/html/mainshell/
RUN chmod -R 777 /var/www/html/mainshell/*

EXPOSE 80
