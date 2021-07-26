package shopping

class ItemStore extends GemericDomainObject {

    Item item
    Boolean isImported = false
    Integer quantity = 0
    BigDecimal cost = 0

    static belongsTo = [item:Item]

    static constraints = {
        isImported nullable: false, blank: false
        item nullable: false, unique: 'isImported'
        quantity min: 0
        cost nullable: false, blank: false, min: new BigDecimal(0)
    }

    static mapping = {
        id generator: 'assigned'
        version false
    }

    @Override
    String toString() {
        return "${isImported ? 'An Imported ' : ''} ${item.toString()}"
    }

}