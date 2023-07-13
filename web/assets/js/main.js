// Preloader
window.addEventListener('load', function () {
    const loader = document.getElementById('preloader');
    loader.style.display = 'none';
});

// Dropdown
let drop = document.querySelectorAll('.dropdown-toggle');
for (var i = 0; i < drop.length; i++) {
    drop[i].addEventListener('click', function () {
        $(this).closest('.dropdown').children('.dropdown-list').slideToggle();
        $(this).toggleClass('active');
    })
}

// Category
var cateSwiper = new Swiper(".category-list", {
    slidesPerView: 'auto',
    spaceBetween: 20,
    freeMode: true,
    autoplay: {
        delay: 5000,
        disableOnInteraction: false,
    },
});

// Product
var proSwiper = new Swiper(".product-list", {
    slidesPerView: 1.5,
    spaceBetween: 10,
    navigation: {
        nextEl: ".swiper-next",
        prevEl: ".swiper-prev",
    },
    breakpoints: {
        768: {
            slidesPerView: 3,
            spaceBetween: 15,
        },
        1024: {
            slidesPerView: 4,
            spaceBetween: 20,
        },
    },
});

// Format number
let price = document.querySelectorAll('.price');
for (let i = 0; i < price.length; i++) {
    let num = Number(price[i].innerHTML).toLocaleString('en');
    price[i].innerHTML = num;
}

// Field Select - Option
let fieldOption = document.querySelectorAll('.field-option-item');
for (let i = 0; i < fieldOption.length; i++) {
    fieldOption[i].addEventListener('click', function () {
        $(this).closest('.field').children('.field-select').val($(this).text());
    })
}

// Toggle Back-to-top
window.addEventListener('scroll', function () {
    var backTop = $('#back-to-top');
    if (window.scrollY > 150) {
        backTop.fadeIn();
    } else {
        backTop.fadeOut();
    }
});

// Back-to-top
let backTop = document.querySelector('#back-to-top');
backTop.addEventListener('click', function () {
    document.body.scrollTop = 0; // For Safari
    document.documentElement.scrollTop = 0; // For Chrome, Firefox, IE and Opera
});

document.querySelectorAll(".modal-toggle").forEach(toggle => {
    toggle.addEventListener("click", () => {
        var modal = document.querySelector(".modal-overlay");
        modal.classList.toggle("open");
    })
})

// Check Image URL
function checkImgUrlExists(url) {
    return new Promise(resolve => {
        let img = new Image();
        img.onload = () => resolve(true);
        img.onerror = () => resolve(false);
        img.src = url;
    });
}