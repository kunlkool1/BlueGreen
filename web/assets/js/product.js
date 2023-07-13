var small = new Swiper(".smallImage", {
    slidesPerView: 4.5,
    spaceBetween: 10,
    freeMode: true,
    watchSlidesProgress: true,
});

var thumbnail = new Swiper(".thumbnail", {
    thumbs: {
        swiper: small,
    },
});

document.querySelector('.detail-quantity .btn-dec').addEventListener('click', () => {
    var qtity = document.querySelector('.detail-quantity input');
    if (qtity.value > 1)
        qtity.value -= 1;
})

document.querySelector('.detail-quantity .btn-inc').addEventListener('click', () => {
    var qtity = document.querySelector('.detail-quantity input');
    qtity.value = parseInt(qtity.value) + 1;
})

function addToCart(id) {
    var quan = document.getElementById('quantity');
    const toastLiveExample = document.getElementById('added');
    const toast = new bootstrap.Toast(toastLiveExample)
    toast.show()
    $.ajax({
        url: "/BlueGreen/add-to-cart",
        data: {
            id: id,
            quantity: quan.value,
        },
        type: "post",
        success: function (response) {
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}