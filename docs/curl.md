# Curl Commands

## Create a Customer

```
 curl -H "Content-type: application/json" -X POST -d '{"username": "MrX", "fullname": "John Smith", "email": "john.smith@example.com", "phone": "613-555-1212", "address": "123 Fake St." }' http://localhost:9551/customers
```

## Get a Customer

This assumes the customer from the last command was created:
```
 curl -X GET http://localhost:9551/customers/MrX
```

## Add Orders to the Customer

```
 curl -H "Content-type: application/json" -X PUT -d '{"orderId": "12345" }' http://localhost:9551/customers/MrX/addorder
```

## Get all Orders for a Customer

```
 curl -X GET http://localhost:9551/customers/MrX/orders
```
