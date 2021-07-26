package shopping

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification


class ItemSpec extends Specification implements DomainUnitTest<Item> {


    void "test something"() {
        setup:
            new ItemType(name: "Food", isTaxble: false).save()
            new ItemType(name: "Taxed", isTaxble: true, taxRate: 5).save()
            new Item(description: "Bread", itemType: ItemType.findByName("Food")).save()
            new Item(description: "Cards", itemType: ItemType.findByName("Taxed")).save()
        expect:
            Item.count() == 2

    }

    void "test missing Description"() {
         when:
            domain.description = null
            domain.validate()
        then:
            domain.errors['description'].code == 'nullable'
    }

    void "Test Missing ItemType"(){
        when:
            domain.description = "TestMe"
            domain.itemType = null
            domain.validate()
        then:
            domain.errors['itemType'].code == 'nullable'
    }

    void "Test Valid Item"(){
        when:
            domain.description = "TestMe"
            domain.itemType = new ItemType(name: "Food", isTaxble: false)
        then:
            domain.validate()
            domain.errors.allErrors.size() == 0
    }

}
