package shopping

import grails.testing.gorm.DataTest
import grails.testing.services.ServiceUnitTest
import org.springframework.test.annotation.Rollback
import grails.test.hibernate.HibernateSpec

@SuppressWarnings(['MethodName', 'DuplicateNumberLiteral'])
@Rollback()
class CartServiceSpec extends HibernateSpec implements DataTest, ServiceUnitTest<CartService> {

    def setupSpec() {
        mockDomain ItemStore
        mockDomain Item
        mockDomain ItemType
    }

    def setup() {
        ItemType taxit = new ItemType(name: "Other", isTaxble: true, taxRate: 10).save()
        ItemType ntaxit = new ItemType(name: "no tax", isTaxble: false).save()

        Item ntaxI = new Item(description: "no Taxed", itemType: ntaxit).save()
        Item taxI = new Item(description: "Taxed", itemType: taxit).save()
        new ItemStore(item: ntaxI, quantity: 1, isImported: false, cost: 2.99).save()
        new ItemStore(item: ntaxI, quantity: 1, isImported: true, cost: 4.99).save()
        new ItemStore(item: taxI, quantity: 1, isImported: true, cost: 13.99).save()
        new ItemStore(item: taxI, quantity: 1, isImported: false, cost: 10.99).save()
    }

    def cleanup() {
    }

    void "test Empty Store"() {
        setup:
        ItemStore.where {}.deleteAll()
        when:
        def result = service.getStore()
        then:
        assert result.size() == 0
    }

    void "test getStore"() {
        when:
        def result = service.getStore()
        then:
        assert result.size() == 4
    }

    void "test getStore with no quantity"() {
        setup:
        ItemStore.where { }.updateAll(quantity : 0)
        when:
        def result = service.getStore()
        then:
        assert result.size() == 0
    }

    void "test getStore with partial quantity"() {
        setup:
        ItemStore itemStore = ItemStore.first()
        itemStore.quantity = 0
        itemStore.save()
        when:
        def result = service.getStore()
        then:
        assert result.size() == 3
    }
}
