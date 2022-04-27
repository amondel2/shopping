package shopping
import groovy.transform.CompileStatic


@Singleton
class Utils {
    @CompileStatic
    String idGenerator(){
        UUID.randomUUID().toString().replaceAll("-", "")
    }

    def round5up(BigDecimal z) {
        def x = Math.floor(z * 100).toInteger()
        x = x + ( 5 - ( x % 5 ?: 5 ) )
        x / 100
    }
    def round5up(def z) {
        round5up(new BigDecimal(z))
    }
}