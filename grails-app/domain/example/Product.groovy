package example

import grails.mongodb.MongoEntity

class Product implements MongoEntity<Product> {

    String name
    Double price
    Integer stars

    static constraints = {
        name blank: false
        price range: 0.0..1000.00
    }
}
