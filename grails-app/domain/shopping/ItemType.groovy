package shopping

class ItemType extends GemericDomainObject {

    String name
    Boolean isTaxble
    BigDecimal taxRate

    static hasMany = [items:Item]

    static constraints = {
        name  minSize: 3, maxSize: 1000, unquie: true
        taxRate nullable: true, blank: false, min: new BigDecimal(0), validator: {value, dobj ->
            if(dobj.isTaxble && value.equals(null) ) {
                return 'default.null.message'
            }
        }
        isTaxble nullable: true, blank: false
    }

    static mapping = {
        id generator: 'assigned'
        version false
    }

    @Override
    String toString() {
        return "${name} is ${isTaxble ? ' taxed at ' + taxRate + '%' : 'not taxed'}"
    }
}
