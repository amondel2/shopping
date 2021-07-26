package shopping

import org.springframework.beans.factory.InitializingBean

class ShoppingCartController implements  InitializingBean {

    static allowedMethods = [checkout : 'POST', index : 'GET']

    CartService cartService
    def index() {
        try {
            def st = cartService.getStore()
            render(view: "index", model: [store: st,error:""])
        } catch (Exception e) {
            log.error("${e.getMessage()} ${e.stackTrace}")
            render(view: "index", model: [store: [], error:"Cart Not Loaded"])
        }
    }

    def checkout(CartCommand cmd) {
        try {
            withForm {
                if(!cmd.hasErrors()) {
                    def st = cartService.getCheckout(cmd)
                    render(view: "receipt", model: [store: st])
                } else {
                    render(view: "index", model: [store: cartService.getStore(),error:cmd.errors.allErrors.join("<br />")])
                }
            }  .invalidToken {
                redirect(action: "index")
            }
        } catch (Exception e) {
            log.error("${e.getMessage()} ${e.stackTrace}")
            render(view: "receipt", model: [store: [], error:"Cart Not Loaded"])
        }
    }
    @Override
    void afterPropertiesSet() throws Exception {
    }
}