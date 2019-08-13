# Shoppingportal Shell

Build Project using below command

npm install && 
npm install https://github.com/meta-magic/amexio-ios/blob/v5.16/amexio-ng-extensions.tgz?raw=true && 
ng build --prod
ls 

sed 's:modules-product-module-ngfactory:product/ui/modules-product-module-ngfactory:g' dist/mainshell/runtime.js  > dist/mainshell/runtime_temp.js 

cat dist/mainshell/runtime_temp.js  > dist/mainshell/runtime.js 

sed 's:modules-orders-module-ngfactory:order/ui/modules-orders-module-ngfactory:g' dist/mainshell/runtime.js  > dist/mainshell/runtime_temp.js 

cat dist/mainshell/runtime_temp.js  > dist/mainshell/runtime.js 
