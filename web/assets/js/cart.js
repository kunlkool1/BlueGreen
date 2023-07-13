function addProduct(btn, action) {
    
    var sub = document.querySelector(".summary .subtotal");
    var grand = document.querySelector(".summary .grandtotal");
    
    if (action == 'increase') {
        var single = btn.closest("tr").querySelector(".single-price");
        sub.textContent = sub.textContent.replaceAll(',', '');
        sub.textContent = parseFloat(sub.textContent) + parseFloat(single.textContent);
        grand.textContent = parseFloat(sub.textContent) + 20;
    }

    if (action == 'decrease') {
        var single = btn.closest("tr").querySelector(".single-price");
        sub.textContent = sub.textContent.replaceAll(',', '');
        sub.textContent = parseFloat(sub.textContent) - parseFloat(single.textContent);
        grand.textContent = parseFloat(sub.textContent) + 20;
    }

    if (action == 'delete') {
        var total = btn.closest("tr").querySelector(".total-price");
        sub.textContent = sub.textContent.replaceAll(',', '');
        sub.textContent = parseFloat(sub.textContent) - parseFloat(total.textContent);
        grand.textContent = parseFloat(sub.textContent) + 20;
    }
    
    $.ajax({
        url: "/BlueGreen/adjustment",
        data: {
            id: btn.value,
            action: action,
        },
        type: "post",
        success: function (response) {
            var cart = document.querySelector("table tbody");
            cart.innerHTML = response;
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });

    setTimeout(function() {
        let price = document.querySelectorAll('.price');
        for (let i = 0; i < price.length; i++) {
            let num = Number(price[i].innerHTML).toLocaleString('en');
            price[i].innerHTML = num;
        }
    }, 200);
}