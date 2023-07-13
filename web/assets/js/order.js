function modifyOrder(id, value) {
    $.ajax({
        url: "/BlueGreen/order",
        data: {
            action: 'update',
            status: value.value,
            orderId: id,
        },
        type: "post",
        success: function (response) {

        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

function filter(date) {
    $.ajax({
        url: "/BlueGreen/order",
        data: {
            date: date.value,
            action: "filter",
        },
        type: "post",
        success: function (response) {
            var accor = document.getElementById('accordionOrder');
            accor.innerHTML = response;
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
    }, 1000);
}

function deleteOrder(id) {
    const search = document.getElementById('search-date');
    console.log('delete')
    $.ajax({
        url: "/BlueGreen/order",
        data: {
            date: search.value,
            action: 'delete',
            orderId: id,
        },
        type: "post",
        success: function (response) {

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
    }, 1000);
}