package shopping

import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class CartLibsTagLibSpec extends Specification implements TagLibUnitTest<CartLibsTagLib> {

    def setup() {
    }

    def cleanup() {
    }

    void "test quantity drop down"() {
        def output = """
         <select name="addToCart" id="addToCart123" style="width:4rem;">
          <option value='1'>1</option> <option value='2'>2</option>
          </select>
"""
        expect:
          tagLib.getQuanityDropDpwn(id:"123",qty: 2).trim().replaceAll("[ ]+"," " ) == output.trim().replaceAll("[ ]+"," " )
    }


    void "test max quantity drop down"() {
        def output = """
         <select name="addToCart" id="addToCart123" style="width:4rem;">
          <option value='1'>1</option> <option value='2'>2</option>
          </select>
"""
        expect:
        (tagLib.getQuanityDropDpwn(id:"123",qty: 1000)  =~ /([<]option value)/).size() == grailsApplication.config.grails.maxCartQty
    }
}
