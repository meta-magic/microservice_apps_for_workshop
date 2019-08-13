# Product UI

Build Project using below command

npm install && 
npm install https://github.com/meta-magic/amexio-ios/blob/v5.16/amexio-ng-extensions.tgz?raw=true && 
ng build --project=productlib && 
npm pack dist/productlib/ && 
npm install productlib-0.0.1.tgz  && 
rm -rf ./node_modules/amexio-ng-extensions/amexio-ng-extensions.es5.js && 
ng build --prod
