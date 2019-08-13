FROM metamagicglobal/apache:jdk8

#RUN rm -rf /etc/apache2/apache2.conf

RUN chmod -R 777 /var/www/html/
RUN mkdir /var/www/html/product/
RUN chmod -R 777  /var/www/html/product/
RUN mkdir /var/www/html/product/ui/
RUN chmod -R 777  /var/www/html/product/ui/
ADD dist/product/modules-product-module-ngfactory.js /var/www/html/product/ui/
RUN chmod -R 777  /var/www/html/product/ui/*


EXPOSE 80
