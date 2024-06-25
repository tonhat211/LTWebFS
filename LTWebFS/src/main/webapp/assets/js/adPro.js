function importProduct() {
    console.log($('#import-product-form'));
    var form = document.querySelector("#import-product-form");
    var formData = new FormData(form);
    var id = formData.get("id");
    var amount = formData.get("amount");
    var action = formData.get("action");
    console.log(id);
    console.log(amount);
    console.log(action);
    var isOk = true;

    if(id === "") {
        document.querySelector('.idinput').hidden = false;
        document.querySelector('.idinput').innerText = "Không được bỏ trống mục này";

        isOk = false;
    } else if (isNaN(id)) {
        console.log(isNaN(id));
        document.querySelector('.idinput').innerText = "Mục này phải là số";
        document.querySelector('.idinput').hidden = false;
        isOk = false;
    } else {
        document.querySelector('.idinput').hidden = true;
    }

    if(amount === "") {
        document.querySelector('.amountinput').hidden = false;
        document.querySelector('.amountinput').innerText = "Không được bỏ trống mục này";
        isOk = false;
    } else if (isNaN(amount)) {
        console.log(isNaN(id));
        document.querySelector('.amountinput').innerText = "Mục này phải là số";
        document.querySelector('.amountinput').hidden = false;
        isOk = false;
    } else {
        document.querySelector('.amountinput').hidden = true;
    }

    if(isOk===false) return;


    $.ajax({
        url: "/LTWebFS/addUpdate-product",
        method: "POST",
        data: {id : id, amount: amount, action :action},
        success: function(data) {
            $("#info-table-body").append(data);
        }
    });
}

function exportProduct() {
    console.log($('#export-product-form'));
    var form = document.querySelector("#export-product-form");
    var formData = new FormData(form);
    var id = formData.get("id");
    var amount = formData.get("amount");
    var action = formData.get("action");
    var reason = formData.get("reason");
    console.log(id);
    console.log(amount);
    console.log(reason);
    var isOk = true;

    if(id === "") {
        document.querySelector('.idinput').hidden = false;
        document.querySelector('.idinput').innerText = "Không được bỏ trống mục này";

        isOk = false;
    } else if (isNaN(id)) {
        console.log(isNaN(id));
        document.querySelector('.idinput').innerText = "Mục này phải là số";
        document.querySelector('.idinput').hidden = false;
        isOk = false;
    } else {
        console.log(isNaN(id));
        document.querySelector('.idinput').hidden = true;

    }

    if(amount === "") {
        document.querySelector('.amountinput').hidden = false;
        document.querySelector('.amountinput').innerText = "Không được bỏ trống mục này";

        isOk = false;
    } else if (isNaN(amount)) {
        console.log(isNaN(id));
        document.querySelector('.amountinput').innerText = "Mục này phải là số";
        document.querySelector('.amountinput').hidden = false;
        isOk = false;
    } else {
        document.querySelector('.amountinput').hidden = true;
    }

    if(isOk===false) return;

    $.ajax({
        url: "/LTWebFS/addUpdate-product",
        method: "POST",
        data: {id : id, amount: amount, action :action, reason: reason},
        success: function(data) {
            $("#info-table-body").append(data);
        }
    });
}

$(document).ready(function() {

    // showSuccessToast("thanh cong tai");
    document.querySelector("#import-product-btn").addEventListener('click',function (event) {

        event.preventDefault();
        $("#product-action").html(`<div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                <div style="width: 100%">

                    <form action="" style="width: 70%; margin: 0 auto" id="import-product-form">
                        <h4 style="text-align: center">Nhập kho</h4>
                        <div class="show-flex-row">
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="id">ID: </label>
                                <input type="text" class="form-control w-80" id="id" name="id" aria-describedby="" placeholder="Nhập ID" value="">
                                <div class="error idinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="amount">Số lượng: </label>
                                <input type="text" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Nhập số lượng" value="">
                                <div class="error amountinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <input type="text" class="form-control w-80" id="action" name="action" aria-describedby="" placeholder="Enter name" value="import" hidden readonly>

                        </div>
                        <div class="show-flex-row" style="justify-content: end">
                            <button class="btn btn-primary" type="button" onclick="importProduct()" style="margin: 10px 10px;">Nhập</button>
                        </div>


                    </form>
    <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
                            <div id="toast-2" style="margin: auto 0">
<!--                                <div class="toastt-2 toast-2&#45;&#45;success">-->
<!--                                    <p class="toast__msg" style="color: white">thanh cong</p>-->
<!--                                </div>-->
                            </div>
                        </div>

                </div>`
        );
        $("#info-table").html(`

        <thead>
            <tr>
                <th scope="col" style="width: 10%;">ID</th>
                <th scope="col" style="width: 60%;">Tên</th>
                <th scope="col" style="width: 10%;">Số lượng</th>
                <th scope="col" style="width: 20%">Thời gian</th>
            </tr>
        </thead>

        <tbody id="info-table-body">
<!--            <tr class="roww">-->
<!--                <th scope="row">12</th>-->
<!--                <td>may loc </td>-->
<!--                <td>100</td>-->
<!--                <td>8213</td>-->
<!--            </tr>-->


        </tbody>`);
    });

    document.querySelector("#export-product-btn").addEventListener('click',function (event) {

        event.preventDefault();
        $("#product-action").html(`<div class="seperate-horizontal-90" style="margin: 20px auto"></div>
                <div style="width: 100%">

                    <form action="" style="width: 70%; margin: 0 auto" id="export-product-form">
                        <h4 style="text-align: center">Xuất kho</h4>
                        <div class="show-flex-row">
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="id">ID: </label>
                                <input type="text" class="form-control w-80" id="id" name="id" aria-describedby="" placeholder="Nhập ID" value="">
                                <div class="error idinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <div class="form-group w-30" style="margin: 0 10px">
                                <label class="w-20" for="amount">Số lượng: </label>
                                <input type="text" class="form-control w-80" id="amount" name="amount" aria-describedby="" placeholder="Nhập số lượng" value="">
                                <div class="error amountinput" hidden>Không được bỏ trống mục này</div>

                            </div>
                            <input type="text" class="form-control w-80" id="action" name="action" aria-describedby="" placeholder="Enter name" value="export" hidden readonly>

                        </div>
                        <div class="form-group w-30" style="margin: 10px 10px">
                            <label class="w-20" for="reason">Nguyên nhân: </label>

                            <textarea  type="text" class="form-control" name="reason" id="reason" rows="6"></textarea>

                        </div>
                        <div class="show-flex-row" style="justify-content: end">
                            <button class="btn btn-primary" type="button" onclick="exportProduct()" style="margin: 10px 10px;">Xuất</button>
                        </div>


                    </form>
    <div class="toastt-container" style="height: 30px; margin-top: 5px; margin-bottom: 5px">
                            <div id="toast-2" style="margin: auto 0">
<!--                                <div class="toastt-2 toast-2&#45;&#45;success">-->
<!--                                    <p class="toast__msg" style="color: white">thanh cong</p>-->
<!--                                </div>-->
                            </div>
                        </div>

                </div>`
        );
        $("#info-table").html(`

        <thead>
            <tr>
                <th scope="col" style="width: 10%;">ID</th>
                <th scope="col" style="width: 60%;">Tên</th>
                <th scope="col" style="width: 10%;">So luong</th>
                <th scope="col" style="width: 20%">Thoi gian</th>
            </tr>
        </thead>

        <tbody id="info-table-body">
<!--            <tr class="roww">-->
<!--                <th scope="row">12</th>-->
<!--                <td>may loc </td>-->
<!--                <td>100</td>-->
<!--                <td>8213</td>-->
<!--            </tr>-->


        </tbody>`);
    });

    // function submitImport() {
    //     var formData =
    // }



});