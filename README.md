# Grails 4 MongoDB Sample Application

This is a Grails 4 sample application with GORM MongoDB 7.0.0

In order to test run the following CURL commands: 

```
curl -XPOST localhost:8080/product -H "Content-Type: application/json" -d '{"name":"Apple", "price":"2.0"}'
curl -XGET localhost:8080/product -H "Content-Type: application/json"'
```

**Please note** that MongoDB is running locally. 
