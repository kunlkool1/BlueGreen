const tabList = document.querySelectorAll(".tab");

tabList.forEach(tab => {
    tab.addEventListener("click", function() {
        var index = tab.getAttribute("tabindex");
        document.querySelectorAll(".tab-pane").forEach(pane => {
            var paneIndex = pane.getAttribute("tab-pane");
            if (index == paneIndex) {
                setTimeout(() => {pane.classList.add("show");}, 100);
                pane.classList.add("active");
            } else {
                setTimeout(() => {pane.classList.remove("show");}, 100);
                pane.classList.remove("active");
            }
        });
        tabList.forEach(tabItem => {
            tabItem.classList.remove("active");
        })
        setTimeout(() => {tab.classList.add("active");}, 200);
    })
})

function changeInfo() {
    const firstName = document.getElementById('txtFirstName');
    const lastName = document.getElementById('txtLastName');
    const phone = document.getElementById('txtPhone');
    const address = document.getElementById('txtAddress');
    const email = document.getElementById('txtEmail');
    
    $.ajax({
        url: "/BlueGreen/myaccount",
        data: {
            action: 'updateInfo',
            txtFirstName: firstName.value,
            txtLastName: lastName.value,
            txtPhone: phone.value,
            txtAddress: address.value,
            txtEmail: email.value,
        },
        type: "post",
        success: function (response) {
            var display = document.querySelector(".tab-pane.show form");
            display.innerHTML = response;
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}

function changePass() {
    const curPass = document.getElementById('txtCurPass');
    const newPass = document.getElementById('txtNewPass');
    const cfmPass = document.getElementById('txtCfmPass');

    $.ajax({
        url: "/BlueGreen/myaccount",
        data: {
            action: 'changePass',
            txtCurPass: curPass.value,
            txtNewPass: newPass.value,
            txtCfmPass: cfmPass.value,
        },
        type: "post",
        success: function (response) {
            var display = document.querySelector(".tab-pane.show form .col-12:nth-of-type(4)");
            display.innerHTML = response;
        },
        error: function (xhr) {
            console.log("ERROR Ajax");
        }
    });
}