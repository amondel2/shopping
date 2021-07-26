package shopping

import spock.lang.Unroll
import grails.testing.services.ServiceUnitTest
import spock.lang.Specification

class UtilsSpec  extends Specification implements ServiceUnitTest<Utils> {

    Utils u

    def setup() {
        u = Utils.getInstance()
    }

    def cleanup() {
    }

    @Unroll("test round5up  '#field' is '#result'")
    void "test round5up"() {
        when:
          def rs = u.round5up(field)
        then:
            result == rs
        where:
            result | field
            3.00   | 2.99
            4.60   | 4.556
            4.55   | 4.55
            4.50   | 4.451
            4.50   | 4.496
            0.5    | 0.5
            0      | 0
            -1.1   | -1.1
           -1.0   | -1.0
            1.4   | 1.354
           -1.35  | -1.354
            0.1    | 0.1
            0.35   | 0.34
            0.35   | "0.34"
    }

    void "test round5up null"() {
        when:
        def rs = u.round5up(null)
        then:
        Exception ex = thrown()
        ex.message.startsWith("Ambiguous method overloading")
    }

    void "test round5up letters"() {
        when:
        def rs = u.round5up("abc")
        then:
        Exception ex = thrown()
        ex.message.startsWith("Character a is neither a decimal digit number")
    }

    void "test idGenerator letters"() {
        when:
        def rs = u.idGenerator()
        then:
        rs.matches("[A-za-z0-9]{32}")
    }

}
