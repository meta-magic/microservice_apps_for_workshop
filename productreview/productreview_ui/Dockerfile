FROM metamagicglobal/apache:jdk8

RUN chmod -R 777 /var/www/html/
RUN mkdir /var/www/html/productreviewui/
RUN chmod -R 777  /var/www/html/productreviewui/
ADD dist/productreviewui/modules-productreview-module-ngfactory.js /var/www/html/productreviewui/
RUN chmod -R 777  /var/www/html/productreviewui/*

EXPOSE 80