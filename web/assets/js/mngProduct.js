function paging_wrap(page) {
    const search = document.getElementById("search-bar");

    $.ajax({
        url: "/BlueGreen/product-management",
        data: {
            page: page,
            search: search.value,
        },
        type: "post",
        success: function (response) {
            var container = document.querySelector(".product-container");
            container.innerHTML = response;
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}