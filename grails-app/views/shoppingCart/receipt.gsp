<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Shopping Cart Order Info</title>
</head>
<body>
<div>
    <div class="${error ? 'errors' : 'hidden'}" >${error}</div>
    <div class="fieldcontain m-1 p-2 border border-dark">
        <h1>You have purchased a total of ${(store?.quantity?.sum()) ?: 0 } item(s)</h1>
        <g:each in="${store}" var="cartItem">
            <g:render template="renderFinal" model="[cartItem:cartItem]" />
        </g:each>
        <div class="flex-md-row fieldcontain">
         <label class="text-left" style="width: 75%">Sales Taxes: $${((store?.taxCost?.sum())?.trunc(2)) ?: '0.00'}</label>
           <hr class="fieldcontain" />
        </div>
        <div class="flex-md-row fieldcontain">
            <label class="text-left" style="width: 75%">Total: $${((store?.baseCost?.sum())?.trunc(2)) ?: '0.00'}</label>
        </div>
    </div>
</div>
</body>
</html>