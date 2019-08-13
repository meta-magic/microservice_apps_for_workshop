FROM metamagicglobal/apache:jdk8

RUN chmod -R 777 /var/www/html/
RUN mkdir /var/www/html/authui/
RUN chmod -R 777  /var/www/html/authui/
ADD dist/auth/modules-auth-module-ngfactory.js /var/www/html/authui/
RUN chmod -R 777  /var/www/html/authui/*

EXPOSE 80