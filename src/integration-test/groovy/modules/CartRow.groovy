package modules

import geb.Module

class CartRow extends Module{
    static content = {

        titleText { $('label').first()}

        quantity {
            $('span').first()?.text().toInteger()
        }
    }
}