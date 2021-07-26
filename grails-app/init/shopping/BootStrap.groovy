package shopping

import shopping.ItemType
import shopping.Item
import shopping.ItemStore

class BootStrap {

    def init = { servletContext ->
        ItemType.withTransaction {
            def foodType = ItemType.findOrCreateWhere(name: "Food", isTaxble: false)
            foodType.save(failOnError: true)

            def medType = ItemType.findOrCreateWhere(name: "Medicine", isTaxble: false)
            medType.save()
            def bookType = ItemType.findOrCreateWhere(name: "Book", isTaxble: false)
            bookType.save()
            def otherType = ItemType.findOrCreateWhere(name: "Other", isTaxble: true, taxRate: new BigDecimal(10))
            otherType.save()

            def bookItem = Item.findOrCreateWhere(description: "Book", itemType: bookType)
            bookItem.save()
            ItemStore.findOrCreateWhere(item: bookItem, cost: new BigDecimal(12.49), isImported: false, quantity: 2).save()
            def musicItem = Item.findOrCreateWhere(description: "music CD", itemType: otherType)
            musicItem.save()
            ItemStore.findOrCreateWhere(item: musicItem, cost: new BigDecimal(14.99), isImported: false, quantity: 2).save()
            def chocolateItem = Item.findOrCreateWhere(description: "chocolate bar", itemType: foodType)
            chocolateItem.save()
            ItemStore.findOrCreateWhere(item: chocolateItem, cost: new BigDecimal(0.85), isImported: false, quantity: 2).save()
            def chocolateBoxItem = Item.findOrCreateWhere(description: "box of chocolates", itemType: foodType)
            chocolateBoxItem.save()
            ItemStore.findOrCreateWhere(item: chocolateBoxItem, cost: new BigDecimal(10.00), isImported: true, quantity: 2).save()
            def perfumeItem = Item.findOrCreateWhere(description: "bottle of perfume", itemType: otherType)
            perfumeItem.save()
            ItemStore.findOrCreateWhere(item: perfumeItem, cost: new BigDecimal(47.50), isImported: true, quantity: 2).save()
            ItemStore.findOrCreateWhere(item: perfumeItem, cost: new BigDecimal(18.99), isImported: false, quantity: 2).save()
            def medItem = Item.findOrCreateWhere(description: "packet of headache pills", itemType: medType)
            medItem.save()
            ItemStore.findOrCreateWhere(item: medItem, cost: new BigDecimal(9.75), isImported: false, quantity: 2).save(flush: true)
        }
    }
    def destroy = {
    }
}
