
## Authenticate User

curl -d '{"userId":"userId","password":"password"}' -H "Content-Type: application/json" -X POST http://35.227.30.18/authms/api/user/auth 

## Add User
curl -d '{"userId":"testuser1","password":"password2","firstName":"firstName","lastName":"lastName","emailId":"emailId","phoneNo":"phoneNo"}' -H "Content-Type: application/json" -X POST http://35.227.30.18/authms/api/user/


## Retrieve All products
curl -H "Content-Type: application/json" -X GET http://35.227.30.18/productms/api/product/

## Retrieve Product by id
curl -H "Content-Type: application/json" -X GET http://35.227.30.18/productms/api/product/d383add3-3761-4bc9-9e7f-8d8c4103628e

## Add product
curl -d '{"productId":"productId","name":"name","description":"description","price":100}' -H "Content-Type: application/json" -X POST http://35.227.30.18/productms/api/product/


## Retrive Reviews By productId
curl -H "Content-Type: application/json" -X GET http://35.227.30.18/productreviewms/api/review/product/d383add3-3761-4bc9-9e7f-8d8c4103628e

## Add Product Review
curl -d '{"productId":"d383add3-3761-4bc9-9e7f-8d8c4103628e","user":"user","reviewdescription":"reviewdescription","rating":"5"}' -H "Content-Type: application/json" -X POST http://35.227.30.18/productreviewms/api/review/

## Review By Id
curl -H "Content-Type: application/json" -X GET http://35.227.30.18/productreviewms/api/review/1a2733e9-a602-4bf5-97c2-168b2a3b395e


## Add To Shoppingcart

curl -d '{"userId":"73e54dcb-e8d3-4757-bb02-e0da1a5d9761","productId":"d383add3-3761-4bc9-9e7f-8d8c4103628e","price":200,"qty":1}' -H "Content-Type: application/json" -X POST http://35.227.30.18/shoppingcartms/api/shoppingcart/

curl -H "Content-Type: application/json" -H "tokenid: 73e54dcb-e8d3-4757-bb02-e0da1a5d9761"  -X GET http://35.227.30.18/shoppingcartms/api/shoppingcart/

## Add Payment

curl -d '{"shoppintCart":[{"productId":"33934cdd-0b6f-48c5-9568-0b30426bad11","price":200,"qty":1},{"productId":"33934cdd-0b6f-48c5-9568-0b30426bad11","price":200,"qty":1}]}' -H "Content-Type: application/json" -H "tokenid: 73e54dcb-e8d3-4757-bb02-e0da1a5d9761" -X POST http://35.227.30.18/paymentms/api/payment/

## Payment by User Id
curl -H "Content-Type: application/json" -H "tokenid: 73e54dcb-e8d3-4757-bb02-e0da1a5d9761"  -X GET  http://35.227.30.18/paymentms/api/payment/

## Payment by Id
curl -H "Content-Type: application/json" -H "tokenid: 73e54dcb-e8d3-4757-bb02-e0da1a5d9761"  -X GET  http://35.227.30.18/paymentms/api/payment/33934cdd-0b6f-48c5-9568-0b30426bad11

## Order by UserId
curl -H "Content-Type: application/json" -H "tokenid: 73e54dcb-e8d3-4757-bb02-e0da1a5d9761"  -X GET http://35.227.30.18/orderms/api/order/
