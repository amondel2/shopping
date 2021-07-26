package shopping

import grails.databinding.BindUsing

class CartCommand implements  grails.validation.Validateable {

    @BindUsing({ object, source ->
        object.quanties = new HashMap<>();
        object.cartItems.each{
            object.quanties.put("${it}",source.map[it]?.toInteger())
        }
        object.state = source.map['state']
    })

    String state
    ArrayList<String> cartItems = cartItems
    HashMap<String,Integer> quanties
    static constraints = {
        state nullable: false, inList: StateService.getInstance().getStates()
        cartItems nullable: false
        quanties nullable: false
    }
}