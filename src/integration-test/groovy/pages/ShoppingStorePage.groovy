package pages

import geb.Page
import geb.module.Select
import modules.CartRow

class ShoppingStorePage extends Page {
    static at = {
        title == "Shopping Store"
    }

    static url = "shoppingCart"

    static content = {
        checkoutButtom { $("#subBtn")}
        clearCartButton { $("#clearBtn")}
        cartItemsContainer { $("#cartItems div[cartparent='1'] ").moduleList(CartRow)}
        cartButtonContainer { $("button[name='cartBtn']") }
        firstAddCartButton { cartButtonContainer.first()}
        secondAddCartButton { cartButtonContainer[1]}
        stateSelect { $("#state").module(Select) }
    }

}
