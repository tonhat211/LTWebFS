function showModalStop() {
    document.querySelector(".confirm-stop").classList.add("active");
}
function showModalResale() {
    document.querySelector(".confirm-resale").classList.add("active");
}

$(document).ready(function() {


    function hideModal() {
        document.querySelector(".confirm-stop").classList.remove("active");
        document.querySelector(".confirm-resale").classList.remove("active");
    }

    // document.querySelector(".btn-stop-pro").addEventListener('click' , showModalStop);
    // document.querySelector(".btn-resale-pro").addEventListener('click' , showModalResale);


    document.querySelector(".confirm-stop .modal__overlay").addEventListener('click' , hideModal);
    document.querySelector(".confirm-resale .modal__overlay").addEventListener('click' , hideModal);
    document.querySelector(".confirm-stop .no-confirm").addEventListener('click' , hideModal);
    document.querySelector(".confirm-resale .no-confirm").addEventListener('click' , hideModal);

    document.querySelector('.yes-confirm').addEventListener('click', function (event) {
        event.preventDefault();
        var urlin = event.target.href;
        availableProduct(urlin);
        hideModal();

    });

    document.querySelector('.yes-resale').addEventListener('click', function (event) {
        event.preventDefault();
        var urlin = event.target.href;
        availableProduct(urlin);
        hideModal();

    });

    function availableProduct(urlin) {
        $.ajax({
            url: urlin,
            method: "POST",
            // data: { action: action, id : id },
            success: function(data) {
                if(data === "stopped") {
                    document.querySelector(".disabled-showing").classList.add("active");
                    document.querySelector(".btn-resale-pro").classList.add("active");
                    document.querySelector(".btn-stop-pro").classList.remove("active");
                    showSuccessToast("Đã khóa sản phẩm thành công");

                } else if(data === "resaled") {
                    document.querySelector(".disabled-showing").classList.remove("active");
                    document.querySelector(".btn-stop-pro").classList.add("active");
                    document.querySelector(".btn-resale-pro").classList.remove("active");
                    showSuccessToast("Mở khóa sản phẩm thành công");

                }
            }
        });
    }
    // function updateProduct(id, name, brand, kind, color, size, wattage, price, amount, yearmade, dateimport, img, description) {

    document.querySelector("#updateForm").addEventListener('submit', function(event) {
        event.preventDefault();
        var formData = new FormData(this);
        var action = formData.get("action");
        var id = formData.get("id");
        var name = formData.get("name");
        var brand = formData.get("brand");
        var kind = formData.get("kind");
        var color = formData.get("color");
        var size = formData.get("size");
        var wattage = formData.get("wattage");
        var price = formData.get("price");
        var amount = formData.get("amount");
        var yearmade = formData.get("yearmade");
        var dateimport = formData.get("dateimport");
        var img = formData.get("img");
        var description = formData.get("description");
        if(action==="update")
            updateProduct(action,id, name, brand, kind, color, size, wattage, price, amount, yearmade, dateimport, img, description);
        else {
            console.log(action);

            this.submit();
            console.log("submit xong");

        }
    });

    function updateProduct(action,id, name, brand, kind, color, size, wattage, price, amount, yearmade, dateimport, img, description) {
        $.ajax({
            url: "/LTWebFS/admin-product",
            method: "POST",
            data: {
                action:action,
                id: id,
                name: name,
                brand: brand,
                kind: kind,
                color: color,
                size: size,
                wattage: wattage,
                price: price,
                amount: amount,
                yearmade: yearmade,
                dateimport: dateimport,
                img: img,
                description: description,
            },
            success: function (data) {
                console.log("hi");
                $('#updateForm').html(data);
            }
        });
    };
});