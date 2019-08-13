FROM metamagicglobal/apache:jdk8

RUN chmod -R 777 /var/www/html/
RUN mkdir /var/www/html/shoppingcartui/
RUN chmod -R 777  /var/www/html/shoppingcartui/
ADD dist/shoppingcartui/modules-shoppingcart-module-ngfactory.js /var/www/html/shoppingcartui/
RUN chmod -R 777  /var/www/html/shoppingcartui/*

EXPOSE 80