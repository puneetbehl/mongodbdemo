package example

import com.mongodb.client.model.Accumulators
import com.mongodb.client.model.Aggregates
import com.mongodb.client.model.Filters
import grails.test.mongodb.MongoSpec
import spock.lang.Shared

class ProductSpec extends MongoSpec {

    public static final Integer THREE_STAR = 3
    public static final Integer FOUR_STAR = 4
    public static final Integer FIVE_STAR = 5
    @Shared List<Product> products = []

    void setup() {
        products << new Product(name: "The Stand", price: 120, stars: THREE_STAR).save(failOnError: true)
        products << new Product(name: "The Jar", price: 120, stars: THREE_STAR).save(failOnError: true)
        products << new Product(name: "Chair", price: 120, stars: THREE_STAR).save(failOnError: true)
        products << new Product(name: "Cooler", price: 120, stars: FOUR_STAR).save(failOnError: true)
        products << new Product(name: "VRV", price: 120, stars: FIVE_STAR).save(failOnError: true)
        mongoSession.flush()
        mongoSession.clear()
    }

    void cleanup() {
        Product.deleteAll(products)
    }

    @Override
    protected List<Class> getDomainClasses() {
        [Product]
    }

    void "test aggregation example"() {

        when:
        def result = Product.collection.aggregate(Arrays.asList(
                Aggregates.match(Filters.gt("price", 100)),
                Aggregates.group('$stars', Accumulators.sum("count", 1))
        ))
        List productGroupedByStars = result.toList()

        then:
        productGroupedByStars.find {it.id == THREE_STAR }.count == "3"
        productGroupedByStars.find {it.id == FOUR_STAR }.count == "1"
        productGroupedByStars.find {it.id == FIVE_STAR }.count == "1"
    }
}
