<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Shopping Store</title>
</head>

<body>
<div>
    <div class="${error ? 'errors' : 'hidden'}" >${error}</div>

    <div class="row fieldcontain">

        <div class="col-lg border-dark border fieldcontain ">
            <h1>Our Store</h1>
            <g:each in="${store}" var="cartItem">
                <g:render template="renderCartItem" model="[cartItem:cartItem]" />
            </g:each>
        </div>
        <div class="col-md-4 border-dark border fieldcontain">
            <h1>Your Cart</h1>

            <g:form  useToken="true" action="checkout" name="chckFrm" method="POST">
                <div id="cartItems"></div>

                <crt:getStateDropDown />
            </g:form>
            <div class="fieldcontain">
                <button class="btn btn-primary" id="subBtn">CheckOut</button>
                <button class="btn btn-secondary align-items-end" id="clearBtn">Clear Cart</button>
            </div>
        </div>
    </div>
</div>
<asset:javascript src="cart.js" />
</body>
</html>