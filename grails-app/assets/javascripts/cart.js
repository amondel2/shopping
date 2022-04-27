$(document).on('click',"button[name='cartBtn']",function(e) {
    try {
        let currentElement = $(e.currentTarget);
        let itemId = $(currentElement).attr('id').replaceAll("addcartbtn_", "");
        //see if this item is already in the cart
        if ($("#cartId-" + itemId).length > 0 && $("#cartId-" + itemId)[0].hasChildNodes()) {
            updateCart(itemId, parseInt($("#addToCart" + itemId).val()),10);
        } else {
            createCartItem(itemId, parseInt($("#addToCart" + itemId).val(),10), currentElement);
        }
    } catch (e) {
        console.error(e.message);
    }
});

function updateCart(itemId,qtyToAdd) {
    let currentqty = parseInt($("#" + itemId).val(),10);
    let maxValue = $("#addToCart" + itemId + " option").last().val();
    qtyToAdd = Math.min(maxValue,currentqty + qtyToAdd);
    $("#" + itemId).val(qtyToAdd);
    $("#cartqty-" + itemId).html(qtyToAdd);
}

function createCartItem(itemId,qtyToAdd,currentElement) {
    let cartItem = "<div cartparent='1' id='cartId-" + itemId + "' > " +
        "<input type='hidden' name='cartItems' value='"+itemId+"' /> " +
        "<input type='hidden' id='"+itemId+"' name='"+itemId+"' value='"+qtyToAdd+"' /> " +
        "<label class='text-left' style='width: 100%'>" + $(currentElement).parent().children("label").first().text() + "</label> <div class='text-left'>Quanity: <span id='cartqty-"+itemId + "'>" + qtyToAdd + "</span></div> " +
        "</div><hr />";
    $("#cartItems").append(cartItem);
}

$(document).ready(function(){
    $("#subBtn").on("click",function (){
        if($("#cartItems")[0].hasChildNodes()) {
            $("#chckFrm").submit();
        } else {
            alert("Add Something to the cart");
        }
    });
    $("#clearBtn").on("click",function (){
        $("#cartItems").empty();
    });
});
