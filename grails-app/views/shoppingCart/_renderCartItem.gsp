<div class="flex-md-row fieldcontain">
    <label class="text-left" style="width: 75%" for="cartItem${cartItem.id}">${cartItem.description} at $${cartItem.baseCost.trunc(2)}</label>
    <input type="hidden" id="cartCost-${cartItem.id}" value="${cartItem.baseCost}" />
    <crt:getQuanityDropDpwn id="${cartItem.id}" qty="${cartItem.quantity}" />
    <button class="btn-primary btn btn-outline-primary" name="cartBtn" id="addcartbtn_${cartItem.id}">Add To Cart</button>
    <hr class="fieldcontain" />
</div>