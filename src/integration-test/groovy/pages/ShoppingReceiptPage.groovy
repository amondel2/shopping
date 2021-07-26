package pages

import geb.Page
import geb.module.Select

class ShoppingReceiptPage extends  Page {
    static at = {
        title == "Shopping Cart Order Info"
    }

    static url = "shoppingCart/receipt"

    static content = {
        summaryTitle { $('h1').text()}
    }
}
