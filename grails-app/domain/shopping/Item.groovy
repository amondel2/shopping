package shopping

import groovy.transform.ToString

class Item extends GemericDomainObject {

    String description
    ItemType itemType

    static hasMany = [itemStores:ItemStore]
    static belongsTo = [itemType:ItemType]

    static constraints = {
        description nullable: false, blank: false, unique: true, minSize: 4, maxSize: 1000
        itemType nullable: false
    }
    static mapping = {
        id generator:'assigned'
        version false
        itemType lazy: false
    }

    @Override
    String toString() {
        return "${description}"
    }
}
