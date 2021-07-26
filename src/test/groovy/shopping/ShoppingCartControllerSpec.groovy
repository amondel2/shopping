package shopping
import grails.testing.web.controllers.ControllerUnitTest
import groovy.mock.interceptor.MockFor
import spock.lang.Specification
import spock.lang.Unroll

import static javax.servlet.http.HttpServletResponse.SC_METHOD_NOT_ALLOWED
import static javax.servlet.http.HttpServletResponse.SC_OK

@SuppressWarnings(['JUnitPublicNonTestMethod', 'JUnitPublicProperty'])
class ShoppingCartControllerSpec extends Specification implements ControllerUnitTest<ShoppingCartController> {
    def setup() {
    }

    def cleanup() {
    }

    void "test index"() {

        given:
        controller.cartService = Stub(CartService) {
            getStore() >> []
        }
        when:
            request.method = 'GET'
            controller.index()

        then:
            response.status == SC_OK
        model.store.size() == 0
    }

    void "test index data"() {

        given:
        controller.cartService = Stub(CartService) {
            getStore() >> [new StoreItem(id: "123123fefwe",description: "HelloWorld",taxCost: 0, baseCost: 2.99, quantity:1)]
        }
        when:
        request.method = 'GET'
        controller.index()

        then:
        response.status == SC_OK
        model.store.size() == 1
        model.store[0].id == "123123fefwe"

    }


    void "test index service Exception"() {

        given:
        def mockCastService = new MockFor(CartService)
        mockCastService.demand.getStore { throw new Exception("I failed") }
        controller.cartService = mockCastService.proxyInstance()
        when:
        request.method = 'GET'
        controller.index()

        then:
        response.status == SC_OK
        model.store.size() == 0
        model.error == "Cart Not Loaded"

    }


    @Unroll
    void "test index Not allowed #method"() {

        given:
        controller.cartService = Stub(CartService) {
            getStore() >> []
        }
        when:
        request.method = method
        controller.index()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'POST', 'PUT']

    }


    @Unroll
    void "test checkout Not allowed #method"() {

        given:
        controller.cartService = Stub(CartService) {
            getStore() >> []
        }

        when:
        request.method = method
        controller.checkout()

        then:
        response.status == SC_METHOD_NOT_ALLOWED

        where:
        method << ['PATCH', 'DELETE', 'GET', 'PUT']

    }
}
