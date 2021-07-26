package shopping

import grails.core.GrailsApplication

class CartLibsTagLib {
    GrailsApplication grailsApplication
    static namespace="crt"
    static defaultEncodeAs = [taglib:'html']
    static encodeAsForTags = [getStateDropDown: 'raw',getQuanityDropDpwn: 'raw']
    def getStateDropDown = {attrs, body ->


        out << body() <<  """
            <label for="state">Select a State</label>
            <select name="state" id="state" required>
            ${StateService.getInstance().getStates().collect{ "<option value='${it}'>${it}</option>" }.join(" ")}
            </select>
        """

    }

    def getQuanityDropDpwn = { attrs, body ->
        out << body() <<  """       
            <select name="addToCart" id="addToCart${attrs.id}" style="width:4rem;">
             ${(1..Math.min(attrs.qty,grailsApplication.config.grails.maxCartQty)).collect{ "<option value='${it}'>${it}</option>" }.join(" ")}
            </select>
        """
    }

}
