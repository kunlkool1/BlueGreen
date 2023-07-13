function filter(page) {
    const brandFil = document.querySelectorAll(".filter-brand input");
    const colorFil = document.querySelectorAll(".filter-color input");
    const cateFil = document.querySelectorAll(".filter-cate input");
    const min = document.getElementById('filter-min');
    const max = document.getElementById('filter-max');
    const search = document.getElementById('search-bar');

    $.ajax({
        url: "/BlueGreen/store",
        data: {
            page: page,
            brands: getCheckedValues(brandFil),
            colors: getCheckedValues(colorFil),
            cates: getCheckedValues(cateFil),
            search: search.value,
            min: min.value,
            max: max.value,
        },
        traditional: true,
        type: "post",
        success: function (response) {
            const display = document.querySelector(".product-display");
            display.innerHTML = response;
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

function getCheckedValues(input) {
    var values = [];
    const brandFil = document.querySelector(".filter-brand input");
    input.forEach(i => {
        if (i.checked) {
            values.push(i.value);
        }
    });
    return values;
}

document.querySelectorAll('input[type=checkbox]').forEach(f => {
    f.addEventListener('change', () => filter(1));
})

document.getElementById('search-bar').addEventListener('input', () => filter(1));