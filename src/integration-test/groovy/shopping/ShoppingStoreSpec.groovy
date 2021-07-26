package shopping

import geb.spock.GebReportingSpec
import grails.testing.mixin.integration.Integration
import pages.ShoppingReceiptPage
import pages.ShoppingStorePage
import spock.lang.Stepwise


@Integration
@Stepwise
class ShoppingStoreSpec extends GebReportingSpec{
    void goToShoppingStorePage() {
        when:
            to ShoppingStorePage
        then:
            at ShoppingStorePage
    }

    void checkEmptyCartPage() {
        String alert
        when:
            alert = withAlert { checkoutButtom.click() }
        then:
            alert == "Add Something to the cart"

    }

    void AddItemToCart() {
        when:
            firstAddCartButton.click()
            secondAddCartButton.click()
        then:
            cartItemsContainer.size() == 2
    }

    void selectASate() {
        when:
            stateSelect.selected = "Delaware"
        then:
            stateSelect.selected == "Delaware"
    }

    void clearCart() {
        when:
            clearCartButton.click()
        then:
            cartItemsContainer.size() == 0
    }

    void checkOut() {
        when:
            firstAddCartButton.click()
            firstAddCartButton.click()
        then:
            cartItemsContainer.size() == 1
            cartItemsContainer[0].quantity == 2
        when:
             checkoutButtom.click()
        then:
            at ShoppingReceiptPage

    }

    void verifySummary() {
        when:
            at ShoppingReceiptPage
        then:
            summaryTitle == "You have purchased a total of 2 item(s)"
    }

}
