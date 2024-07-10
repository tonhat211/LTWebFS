const p$ = document.querySelector.bind(document);
const p$$ = document.querySelectorAll.bind(document);
const items = p$$('.form-check-input');
const totalMoney = p$('.totalMoney');
const totalProduct = p$('.totalProduct');
const chooseAll = p$('.chooseAll');

// chon tat ca san pham
chooseAll.addEventListener('change',function (){
    items.forEach(function(item) {
        item.checked = chooseAll.checked;
    });

    if(chooseAll.checked) {
        totalProduct.textContent = items.length;
        updatePrice();


    } else {
        totalProduct.textContent = 0;
        totalMoney.textContent = 0;

    }


});

// kiem tra xem tat ca boxcheck co duoc check khong
function isAllChecked() {
    var isAllChecked = true;
    items.forEach(function(item) {

        if(item.checked == false) {
            isAllChecked = false;
        }
    });

    if(isAllChecked==true) {
        chooseAll.checked =true;
    }

}

// cap nhat gia
items.forEach(function(item) {

    item.addEventListener('change', function() {
        updatePrice();
    });

    isAllChecked();
});

function updatePrice() {
    var total = 0;
    var totalPro = 0;
    const items = p$$('.form-check-input');
    var isAllChecked = true;
    items.forEach(function(chk) {
        if (chk.checked) {
            var priceUnit = parseFloat(chk.parentNode.parentNode.parentNode.querySelector('.price').innerText);
            var qty=  chk.parentNode.parentNode.parentNode.querySelector('.product-qty-control .qty').value;
            total += parseInt(priceUnit * qty);
            totalPro+=1;

        } else {
            chooseAll.checked = false;
            isAllChecked = false;
        }
    });

    if(isAllChecked ==true) {
        chooseAll.checked = true;
    }

    const VND = new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND',
    });

    // console.log("updated")
    totalMoney.textContent = total.toLocaleString('vi-VN') +".000";
    // totalMoney.textContent = VND.format(total);
    totalProduct.textContent = totalPro;
}


// check xem co san pham nao duoc chon chua
document.getElementById('myForm').onsubmit = function() {

    var checkboxes = document.querySelectorAll('input[type="checkbox"]');
    var checked = false;
    for (var i = 0; i < checkboxes.length; i++) {
        if (checkboxes[i].checked) {
            checked = true;
            break;
        }
    }

    if (!checked) {
        alert('Chưa có sản phẩm nào được chọn!');
        return false;
    }
};


//     cap nhat so luong san pham
$(document).ready(function() {
    $(".increaseBtn").click(function() {
        var cartId = $(this).siblings(".idcart").val();
        var productId = $(this).siblings(".idproduct").val(); // Lấy ID sản phẩm
        var quantityInput = $(this).siblings(".product-qty-input.qty");
        var currentQuantity = parseInt(quantityInput.val());
        quantityInput.val(currentQuantity + 1); // Tăng số lượng lên 1
        updateQuantity(cartId, productId, quantityInput.val()); // Gọi hàm cập nhật số lượng
    });

    $(".decreaseBtn").click(function() {
        var cartId = $(this).siblings(".idcart").val();
        var productId = $(this).siblings(".idproduct").val(); // Lấy ID sản phẩm
        var quantityInput = $(this).siblings(".product-qty-input.qty");
        var currentQuantity = parseInt(quantityInput.val());
        // if (currentQuantity > 1) {
            quantityInput.val(currentQuantity - 1); // Giảm số lượng đi 1
        if(currentQuantity -1 == 0) {
            var productElement = document.getElementById("item" + cartId + "=" + productId);
            if (productElement) {
                productElement.remove(); // Xóa phần tử khỏi DOM
            }
        }
        // console.log($(".totalProduct").innerText);

        updateQuantity(cartId, productId, quantityInput.val()); // Gọi hàm cập nhật số lượng
        // }


    });

    $(".deleteProductBtn").click(function() {
        var productId = this.parentNode.parentNode.querySelector('.idproduct').value;
        var cartId = this.parentNode.parentNode.querySelector('.idcart').value;
        var currentQuantity = 1;

        var productElement = document.getElementById("item" + cartId + "=" + productId);
        if (productElement) {
            productElement.remove(); // Xóa phần tử khỏi DOM
        }


        // $(".totalProduct").innerText = parseInt($(".totalProduct").innerText) -1;
        updateQuantity(cartId, productId, 0);





    });


    // Hàm cập nhật số lượng
    function updateQuantity(cartId, productId, quantity) {
        console.log("cap nhat so luong");
        console.log("so luong cu: " + quantity);
        $.ajax({
            url: "/LTWebFS/update-cart", // Đường dẫn đến Servlet
            method: "POST",
            data: { cartId:cartId, productId: productId, quantity: quantity },
            success: function(data) {
                updatePrice();
                // alert(data);
            }
        });
    }

});