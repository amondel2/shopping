package shopping

import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional
import org.hibernate.FetchMode as FM

@Transactional
class CartService {

    GrailsApplication grailsApplication

    def getStore() {
        ItemStore.withCriteria {
            gt("quantity",0)
        }.collect { ItemStore it ->
            new StoreItem( id: it.id,description: it.toString(),quantity: it.quantity,taxCost: 0, baseCost: it.cost)
        }
    }

    def getCheckout(CartCommand params) {
        def skipTax = params.state.trim().toUpperCase().startsWith('M')
        ItemStore.withTransaction { status ->
            try {
                ItemStore.withCriteria {
                    inList("id", params.cartItems)
                    fetchMode("item", FM.JOIN)
                    lock true
                }.collect { ItemStore item ->
                    try {
                        def qty = checkQty(params.quanties, item)
                        if (qty <= 0) {
                            return null
                        }
                        updateQty(qty, item)
                        def cost = item.cost + qty
                        def taxCost = calculateTax(!skipTax && item.item.itemType.isTaxble, item, cost)
                        new StoreItem( id: item.id,description: item.toString(),quantity: qty, taxCost: taxCost , baseCost: cost + taxCost)
                    } catch (Exception e) {
                        null
                    }
                } - [null]  //Removes null values from being shown
            } catch (Exception e) {
                status.setRollbackOnly();
                throw e
            }
        }
    }

    def updateQty(qty,ItemStore itemStore) {
        itemStore.quantity -= qty
        itemStore.save(failOnError:true)
    }

    def checkQty(qtyList, ItemStore item) {
        def qty = qtyList.find { node ->
            node.key == item.id
        }?.value ?: 0
        Math.min(qty,item.quantity)
    }


    def calculateTax(payTax, ItemStore itemStore, cost) {
        def taxAmount = 0
        if(itemStore.isImported) {
            taxAmount  = Utils.getInstance().round5up(cost * (grailsApplication.config.grails.importPercent / 100))
        }
        if(payTax) {
            taxAmount += Utils.getInstance().round5up(cost * (itemStore.item.itemType.taxRate / 100))
        }
        taxAmount
    }
}
