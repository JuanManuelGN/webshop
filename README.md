# webshop
## API
### Add customer
post
http://localhost:8080/api/addCustomer
body:
{
	"name": "Juan",
	"email": "juan@email.com",
	"account": "ES1000"
}
### Add product to store
post
http://localhost:8080/api/addProductToStore
body:
{
	"productId": "beer",
	"price": 1.5,
	"description": "Estrella Galicia"
}
### Add product to shopping card
post
http://localhost:8080/api/addProductToShoppingCard
body:
{
	"productId": "milk",
	"count": 1,
	"customer": "juan@email.com"
}
### Pay
post
http://localhost:8080/api/pay
body:
{
	"email": "juan@email.com"
}